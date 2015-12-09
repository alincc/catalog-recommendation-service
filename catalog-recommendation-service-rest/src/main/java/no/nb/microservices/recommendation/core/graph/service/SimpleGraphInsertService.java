package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.build.*;
import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.core.graph.repository.*;
import no.nb.microservices.recommendation.model.Item;
import no.nb.microservices.recommendation.model.ItemAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SimpleGraphInsertService implements GraphInsertService {

    private final Logger log = LoggerFactory.getLogger(SimpleGraphInsertService.class);
    private final ItemRepository itemRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SearchRepository searchRepository;
    private final LocationRepository locationRepository;
    private final PublisherRepository publisherRepository;
    private final SearchQueryRepository searchQueryRepository;

    @Autowired
    public SimpleGraphInsertService(ItemRepository itemRepository, SessionRepository sessionRepository, UserRepository userRepository, SearchRepository searchRepository, LocationRepository locationRepository, PublisherRepository publisherRepository, SearchQueryRepository searchQueryRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.searchRepository = searchRepository;
        this.locationRepository = locationRepository;
        this.publisherRepository = publisherRepository;
        this.searchQueryRepository = searchQueryRepository;
    }

    @Transactional
    public void addItemAction(ItemAction itemAction) {
        // Finds user
        UserNode user = userRepository.merge(new UserNodeBuilder(itemAction.getUser()).build());

        // Finds session
        Optional<SessionNode> sessionOptional = user.getSessionNodes().stream().filter(q -> q.getSessionId().equalsIgnoreCase(itemAction.getSession().getSessionId())).findFirst();
        SessionNode session = (sessionOptional.isPresent() ? sessionOptional.get() : sessionRepository.save(new SessionNodeBuilder(itemAction.getSession()).build()));

        // Finds item
        ItemNode item = itemRepository.findByItemId(itemAction.getItemId());

        session.addAction(item, itemAction.getAction());
        session.addSearch(new SearchNodeBuilder(itemAction, session, item, searchQueryRepository).build());
        session.setLocation(new LocationNodeBuilder(itemAction.getSession().getLocation(), session, locationRepository).build());

        user.addSession(session);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addItem(Item item) {
        ItemNode itemNode = itemRepository.findByItemId(item.getItemId());
        if (itemNode == null) {
            itemNode = new ItemNode(item.getItemId(), item.getMediaType(), item.getTopics());
            itemNode.setPublisher(new PublisherNodeBuilder(item.getPublisher(), publisherRepository).build());
            itemNode.setLocation(new LocationNodeBuilder(item.getLocation(), locationRepository).build());

            itemRepository.save(itemNode);
        }
    }
}

package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.build.ItemService;
import no.nb.microservices.recommendation.core.graph.build.SearchService;
import no.nb.microservices.recommendation.core.graph.build.SessionService;
import no.nb.microservices.recommendation.core.graph.build.UserService;
import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.model.query.Item;
import no.nb.microservices.recommendation.model.query.ItemAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SimpleGraphInsertService implements GraphInsertService {

    private final Logger log = LoggerFactory.getLogger(SimpleGraphInsertService.class);
    private final UserService userService;
    private final SearchService searchService;
    private final SessionService sessionService;
    private final ItemService itemService;

    @Autowired
    public SimpleGraphInsertService(UserService userService, SearchService searchService,
                                    SessionService sessionService, ItemService itemService) {
        this.userService = userService;
        this.searchService = searchService;
        this.sessionService = sessionService;
        this.itemService = itemService;
    }

    @Transactional
    public void addItemAction(ItemAction itemAction) {
        // Finds user
        UserNode user = userService.getUser(itemAction.getUser());

        // Finds session
        SessionNode session = sessionService.getSession(user, itemAction.getSession());

        // Finds item
        ItemNode item = itemService.getItem(itemAction.getItemId());

        session.addAction(item, itemAction.getAction());
        session.addSearch(searchService.getSearchNode(session, item, itemAction));

        if(user != null) {
            user.addSession(session);
            userService.save(user);
        } else if (session != null) {
            sessionService.saveSession(session);
        }
    }

    @Override
    @Transactional
    public void addItem(Item item) {
        itemService.saveItem(item);
    }
}

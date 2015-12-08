package no.nb.microservices.clickstream.core.graph.service;

import no.nb.microservices.clickstream.core.graph.model.node.*;
import no.nb.microservices.clickstream.core.graph.repository.*;
import no.nb.microservices.clickstream.model.ActionItem;
import no.nb.microservices.clickstream.model.Item;
import no.nb.microservices.clickstream.rest.assembler.LocationNodeBuilder;
import no.nb.microservices.clickstream.rest.assembler.SessionNodeBuilder;
import no.nb.microservices.clickstream.rest.assembler.UserNodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ClickstreamService implements IClickstreamService {

    private final Logger log = LoggerFactory.getLogger(ClickstreamService.class);
    private final ItemRepository itemRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SearchRepository searchRepository;
    private final LocationRepository locationRepository;
    private final PublisherRepository publisherRepository;
    private final SearchQueryRepository searchQueryRepository;

    @Autowired
    public ClickstreamService(ItemRepository itemRepository, SessionRepository sessionRepository, UserRepository userRepository, SearchRepository searchRepository, LocationRepository locationRepository, PublisherRepository publisherRepository, SearchQueryRepository searchQueryRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.searchRepository = searchRepository;
        this.locationRepository = locationRepository;
        this.publisherRepository = publisherRepository;
        this.searchQueryRepository = searchQueryRepository;
    }

    @Transactional
    public void addActionItem(ActionItem actionItem) {
        // Finds user
        UserNode user = userRepository.merge(new UserNodeBuilder(actionItem.getUser()).build());

        // Finds session
        Optional<SessionNode> sessionOptional = user.getSessionNodes().stream().filter(q -> q.getSessionId().equalsIgnoreCase(actionItem.getSession().getSessionId())).findFirst();
        SessionNode session = (sessionOptional.isPresent() ? sessionOptional.get() : sessionRepository.save(new SessionNodeBuilder(actionItem.getSession()).build()));

        // Finds item and adds action
        ItemNode item = itemRepository.findByItemId(actionItem.getItemId());
        session.addAction(item, actionItem.getAction());

        // Sets query
        if (!StringUtils.isEmpty(actionItem.getQuery())) {
            Optional<SearchNode> searchNodeOptional = session.getSearches().stream()
                    .filter(q -> q.getSearchQuery().getQuery().equalsIgnoreCase(actionItem.getQuery())).findFirst();
            SearchNode searchNode = (searchNodeOptional.isPresent()) ? searchNodeOptional.get() : new SearchNode();

            SearchQueryNode searchQuery = searchQueryRepository.findByQuery(actionItem.getQuery());
            if (searchQuery == null) {
                searchQuery = searchQueryRepository.save(new SearchQueryNode(actionItem.getQuery()));
            }

            searchNode.setSearchQuery(searchQuery);
            session.addSearch(searchNode);
            searchNode.addAction(item, actionItem.getAction());
        }

        // Sets session location
        if (actionItem.getSession().getLocation() != null && session.getLocation() == null) {
            LocationNode location = locationRepository.findByMunicipalityAndCounty(actionItem.getSession().getLocation().getMunicipality(), actionItem.getSession().getLocation().getCounty());
            session.setLocation((location == null) ? new LocationNodeBuilder(actionItem.getSession().getLocation()).build() : location);
        }

        user.addSession(session);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addItem(Item item) {
        ItemNode itemNode = itemRepository.findByItemId(item.getItemId());
        if (itemNode == null) {
            itemNode = new ItemNode(item.getItemId(), item.getMediaType(), item.getTopics());
            PublisherNode publisherNode = publisherRepository.findByName(item.getPublisher());
            LocationNode locationNode = locationRepository.findByMunicipalityAndCounty(item.getLocation().getMunicipality(), item.getLocation().getCounty());

            if (publisherNode == null) {
                publisherNode = publisherRepository.save(new PublisherNode(item.getPublisher()));
            }

            if (locationNode == null) {
                locationNode = locationRepository.save(new LocationNodeBuilder(item.getLocation()).build());
            }

            itemNode.setPublisher(publisherNode);
            itemNode.setLocation(locationNode);

            itemRepository.save(itemNode);
        }
    }


//    @Override
//    public Activity addActivity(String sessionId, String url, String sesamid, String mediatype) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        SessionNode sessionNode = userRepository.save(new SessionNode(sessionId));
//        ItemNode nbUrl = itemRepository.save(new ItemNode(url, sesamid, mediatype));
//        Visited visited = visitedRepository.save(new Visited(sessionNode, nbUrl, System.currentTimeMillis()));
//        stopWatch.stop();
//        log.debug("Used {} ms to add '{}' to clickstream for user '{}'", stopWatch.getTime(), url, sessionId);
//        if (visited != null) {
//            new Activity(sessionId, url, sesamid, mediatype);
//        }
//        return null;
//    }

//    @Override
//    public List<TopVisitedUrl> getTopVisitedUrls(String mediatype, int count) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        List<TopVisitedUrl> topVisitedUrls = itemRepository.getTopVisitedUrls(mediatype, count);
//        stopWatch.stop();
//        log.debug("Used {} ms to get top {} visted {}", stopWatch.getTime(), count, mediatype);
//        return topVisitedUrls;
//    }
//
//    @Override
//    public List<NbUrl> getActivities(String sessionId) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        List<NbUrl> clickstream = userRepository.getClickstream(sessionId);
//        stopWatch.stop();
//        log.debug("Used {} ms to get clickstream for user '{}'", stopWatch.getTime(), sessionId);
//        return clickstream;
//    }
//
//    @Override
//    public List<NbUrl> getLastVisited(String sessionId, int count) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        List<NbUrl> lastVisited = userRepository.getLastVisited(sessionId, count);
//        stopWatch.stop();
//        log.debug("Used {} ms to get the {} last visited items for user '{}'", stopWatch.getTime(), count, sessionId);
//        return lastVisited;
//    }
//
//    @Override
//    public List<NbUrl> recommend(String sesamid, String mediatype, int count) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        List<NbUrl> recommend = itemRepository.recommend(sesamid, mediatype, count);
//        stopWatch.stop();
//        log.debug("Used {} ms to recommend {} {} for '{}'", stopWatch.getTime(), count, mediatype, sesamid);
//        return recommend;
//    }
//
//    @Override
//    public Page<Activity> getActivities(String id, PageRequest pageRequest) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Page<Activity> activities = userRepository.getActivities(id, pageRequest);
//        stopWatch.stop();
//        log.debug("Used {} ms to get clickstream for user '{}'", stopWatch.getTime(), id);
//        return activities;
//    }
//
//    @Override
//    public Activity getActivity(String id, String activityId) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Activity activity = userRepository.getActivity(id, activityId);
//        stopWatch.stop();
//        log.debug("Used {} ms to get clickstream for user '{}'", stopWatch.getTime(), id);
//        return activity;
//    }
//
//    @Override
//    public Page<NbUrl> getAllUrls(PageRequest pageRequest) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Page<NbUrl> urls = itemRepository.getAllUrls(pageRequest);
//        stopWatch.stop();
//        log.debug("Used {} ms to get all urls", stopWatch.getTime());
//        return urls;
//    }
//
//    @Override
//    public Page<NbUrl> getRecommendations(String sesamid, PageRequest pageRequest) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Page<NbUrl> urls = itemRepository.getRecommendation(sesamid, pageRequest);
//        stopWatch.stop();
//        log.debug("Used {} ms to recommend urls for other users", stopWatch.getTime());
//        return urls;
//    }
//
//    @Override
//    public Page<NbUrl> getMostPopular(String mediatype, PageRequest pageRequest) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Page<NbUrl> urls;
//        if (!mediatype.equalsIgnoreCase("all")) {
//            urls = itemRepository.getMostPopular(mediatype, pageRequest);
//        } else {
//            urls = itemRepository.getMostPopular(pageRequest);
//        }
//        stopWatch.stop();
//        log.debug("Used {} ms to get all urls", stopWatch.getTime());
//        return urls;
//    }
}

package no.nb.microservices.clickstream.core.graph.service;

import no.nb.microservices.clickstream.core.graph.model.node.*;
import no.nb.microservices.clickstream.core.graph.repository.*;
import no.nb.microservices.clickstream.model.ActionItem;
import no.nb.microservices.clickstream.rest.assembler.ItemBuilder;
import no.nb.microservices.clickstream.rest.assembler.SessionBuilder;
import no.nb.microservices.clickstream.rest.assembler.UserBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClickstreamService implements IClickstreamService {

    private final Logger log = LoggerFactory.getLogger(ClickstreamService.class);
    private final ItemRepository itemRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SearchRepository searchRepository;
    private final LocationRepository locationRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public ClickstreamService(ItemRepository itemRepository, SessionRepository sessionRepository, UserRepository userRepository, SearchRepository searchRepository, LocationRepository locationRepository, PublisherRepository publisherRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.searchRepository = searchRepository;
        this.locationRepository = locationRepository;
        this.publisherRepository = publisherRepository;
    }

    public void addActionItem(ActionItem actionItem) {
        Item item = itemRepository.merge(new ItemBuilder(actionItem.getItem()).build());
        Session session = sessionRepository.merge(new SessionBuilder(actionItem.getSession()).build());
        User user = userRepository.merge(new UserBuilder(actionItem.getUser()).build());

        if (!StringUtils.isEmpty(actionItem.getQuery())) {
            Search search = searchRepository.merge(new Search(actionItem.getQuery()));
            session.addSearch(search);
            search.addAction(item, actionItem.getAction());
        }
        else {
            session.addAction(item, actionItem.getAction());
        }

        if (actionItem.getSession().getLocation() != null) {
            Location location = locationRepository.merge(new Location(
                    actionItem.getSession().getLocation() .getMunicipality(),
                    actionItem.getSession().getLocation() .getCounty(),
                    actionItem.getSession().getLocation() .getCountry()));
            session.setLocation(location);
        }

        if (actionItem.getItem().getLocation() != null) {
            Location location = locationRepository.merge(new Location(
                    actionItem.getItem().getLocation().getMunicipality(),
                    actionItem.getItem().getLocation().getCounty(),
                    actionItem.getItem().getLocation().getCountry()));
            item.setLocation(location);
        }

        if (!StringUtils.isEmpty(actionItem.getItem().getPublisher())) {
            Publisher publisher = publisherRepository.merge(new Publisher(actionItem.getItem().getPublisher()));
            item.setPublisher(publisher);
        }

        user.addSession(session);

        userRepository.save(user);
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

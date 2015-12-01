package no.nb.microservices.clickstream.core.graph.service;

import no.nb.microservices.clickstream.core.graph.repository.IUrlRepository;
import no.nb.microservices.clickstream.core.graph.repository.IUserRepository;
import no.nb.microservices.clickstream.core.graph.repository.IVisitedRepository;
import no.nb.microservices.clickstream.model.*;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ClickstreamService implements IClickstreamService {

    private final Logger log = LoggerFactory.getLogger(ClickstreamService.class);
    private final IUrlRepository urlRepository;
    private final IUserRepository userRepository;
    private final IVisitedRepository visitedRepository;

    @Autowired
    public ClickstreamService(IUrlRepository urlRepository, IUserRepository userRepository, IVisitedRepository visitedRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
        this.visitedRepository = visitedRepository;
    }

    @Override
    public Activity addActivity(String sessionId, String url, String sesamid, String mediatype) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        NbUser nbUser = userRepository.save(new NbUser(sessionId));
        NbUrlNode nbUrl = urlRepository.save(new NbUrlNode(url, sesamid, mediatype));
        Visited visited = visitedRepository.save(new Visited(nbUser, nbUrl, System.currentTimeMillis()));
        stopWatch.stop();
        log.debug("Used {} ms to add '{}' to clickstream for user '{}'", stopWatch.getTime(), url, sessionId);
        if (visited != null) {
            new Activity(sessionId, url, sesamid, mediatype);
        }
        return null;
    }

    @Override
    public List<TopVisitedUrl> getTopVisitedUrls(String mediatype, int count) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<TopVisitedUrl> topVisitedUrls = urlRepository.getTopVisitedUrls(mediatype, count);
        stopWatch.stop();
        log.debug("Used {} ms to get top {} visted {}", stopWatch.getTime(), count, mediatype);
        return topVisitedUrls;
    }

    @Override
    public List<NbUrl> getActivities(String sessionId) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<NbUrl> clickstream = userRepository.getClickstream(sessionId);
        stopWatch.stop();
        log.debug("Used {} ms to get clickstream for user '{}'", stopWatch.getTime(), sessionId);
        return clickstream;
    }

    @Override
    public List<NbUrl> getLastVisited(String sessionId, int count) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<NbUrl> lastVisited = userRepository.getLastVisited(sessionId, count);
        stopWatch.stop();
        log.debug("Used {} ms to get the {} last visited items for user '{}'", stopWatch.getTime(), count, sessionId);
        return lastVisited;
    }

    @Override
    public List<NbUrl> recommend(String sesamid, String mediatype, int count) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<NbUrl> recommend = urlRepository.recommend(sesamid, mediatype, count);
        stopWatch.stop();
        log.debug("Used {} ms to recommend {} {} for '{}'", stopWatch.getTime(), count, mediatype, sesamid);
        return recommend;
    }

    @Override
    public Page<Activity> getActivities(String id, PageRequest pageRequest) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Page<Activity> activities = userRepository.getActivities(id, pageRequest);
        stopWatch.stop();
        log.debug("Used {} ms to get clickstream for user '{}'", stopWatch.getTime(), id);
        return activities;
    }

    @Override
    public Activity getActivity(String id, String activityId) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Activity activity = userRepository.getActivity(id, activityId);
        stopWatch.stop();
        log.debug("Used {} ms to get clickstream for user '{}'", stopWatch.getTime(), id);
        return activity;
    }

    @Override
    public Page<NbUrl> getAllUrls(PageRequest pageRequest) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Page<NbUrl> urls = urlRepository.getAllUrls(pageRequest);
        stopWatch.stop();
        log.debug("Used {} ms to get all urls", stopWatch.getTime());
        return urls;
    }

    @Override
    public Page<NbUrl> getRecommendations(String sesamid, PageRequest pageRequest) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Page<NbUrl> urls = urlRepository.getRecommendation(sesamid, pageRequest);
        stopWatch.stop();
        log.debug("Used {} ms to recommend urls for other users", stopWatch.getTime());
        return urls;
    }

    @Override
    public Page<NbUrl> getMostPopular(String mediatype, PageRequest pageRequest) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Page<NbUrl> urls;
        if (!mediatype.equalsIgnoreCase("all")) {
            urls = urlRepository.getMostPopular(mediatype, pageRequest);
        } else {
            urls = urlRepository.getMostPopular(pageRequest);
        }
        stopWatch.stop();
        log.debug("Used {} ms to get all urls", stopWatch.getTime());
        return urls;
    }
}

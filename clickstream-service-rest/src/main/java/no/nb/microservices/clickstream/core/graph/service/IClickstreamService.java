package no.nb.microservices.clickstream.core.graph.service;

import no.nb.microservices.clickstream.model.Activity;
import no.nb.microservices.clickstream.model.NbUrl;
import no.nb.microservices.clickstream.model.TopVisitedUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IClickstreamService {

    Activity addActivity(String sessionId, String url, String sesamid, String mediatype);

    List<TopVisitedUrl> getTopVisitedUrls(String mediatype, int count);

    List<NbUrl> getActivities(String sessionId);

    List<NbUrl> getLastVisited(String sessionId, int count);

    List<NbUrl> recommend(String sesamid, String mediatype, int count);

    Page<Activity> getActivities(String id, PageRequest pageRequest);

    Activity getActivity(String id, String activityId);

    Page<NbUrl> getAllUrls(PageRequest pageRequest);

    Page<NbUrl> getRecommendations(String sesamid, PageRequest pageRequest);

    Page<NbUrl> getMostPopular(String mediatype, PageRequest pageRequest);
}

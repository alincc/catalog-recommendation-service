package no.nb.microservices.clickstream.core.graph.service;

import no.nb.microservices.clickstream.core.graph.model.node.ItemNode;
import no.nb.microservices.clickstream.model.ActionItem;
import no.nb.microservices.clickstream.model.Item;

public interface IClickstreamService {
    void addActionItem(ActionItem actionItem);

    void addItem(Item item);

//    Activity addActivity(String sessionId, String url, String sesamid, String mediatype);
//
//    List<TopVisitedUrl> getTopVisitedUrls(String mediatype, int count);
//
//    List<NbUrl> getActivities(String sessionId);
//
//    List<NbUrl> getLastVisited(String sessionId, int count);
//
//    List<NbUrl> recommend(String sesamid, String mediatype, int count);
//
//    Page<Activity> getActivities(String id, PageRequest pageRequest);
//
//    Activity getActivity(String id, String activityId);
//
//    Page<NbUrl> getAllUrls(PageRequest pageRequest);
//
//    Page<NbUrl> getRecommendations(String sesamid, PageRequest pageRequest);
//
//    Page<NbUrl> getMostPopular(String mediatype, PageRequest pageRequest);
}

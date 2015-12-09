package no.nb.microservices.recommendation.core.graph.service;


import no.nb.microservices.recommendation.model.Item;
import no.nb.microservices.recommendation.model.ItemAction;

public interface IClickstreamService {
    void addItemAction(ItemAction itemAction);

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

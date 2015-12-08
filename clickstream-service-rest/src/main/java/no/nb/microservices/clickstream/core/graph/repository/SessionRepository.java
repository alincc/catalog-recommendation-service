package no.nb.microservices.clickstream.core.graph.repository;

import no.nb.microservices.clickstream.core.graph.model.node.SessionNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface SessionRepository extends GraphRepository<SessionNode> {

    SessionNode findBySessionId(String sessionId);

    @Query("MERGE (s:Session { sessionId: {0}.sessionId }) RETURN s")
    SessionNode merge(SessionNode session);

//    @Query("MATCH ( user:User { sessionId: {0}} )-[r:VISITED]->(url:Url) RETURN url.value AS value, url.sesamid AS sesamid, url.mediatype AS mediatype ORDER BY r.timestamp ASC")
//    List<NbUrl> getClickstream(String sessionId);
//
//    @Query("MATCH ( user:User { sessionId: {0}} )-[r:VISITED]->(url:Url) RETURN url ORDER BY r.timestamp ASC")
//    List<ItemNode> getClickstream2(String sessionId);
//
//    @Query("MATCH (urls:Url)<-[r:VISITED]-(:User) "
//            + "WHERE urls.mediatype = {0} "
//            + "RETURN urls.value AS url, urls.sesamid AS sesamid, COUNT(r) AS count ORDER BY COUNT(r) DESC LIMIT {1}")
//    List<TopVisitedUrl> getTopVisitedUrls(String mediatype, int count);
//
//    @Query("MATCH ( user:User { sessionId: {0}} )-[r:VISITED]->(url:Url) USING INDEX user:User(sessionId) RETURN url.value AS value, url.sesamid AS sesamid, url.mediatype AS mediatype ORDER BY r.timestamp ASC")
//    List<NbUrl> getClickstreamV2(String sessionId);
//
//    @Query("MATCH (user:User {sessionId: {0}})-[r:VISITED]->(url:Url) "
//            + "WHERE has(url.sesamid) "
//            + "WITH url, r ORDER BY r.timestamp DESC "
//            + "RETURN DISTINCT url.value AS value, url.sesamid AS sesamid, url.mediatype AS mediatype LIMIT {1}")
//    List<NbUrl> getLastVisited(String sessionId, int count);
//
//    @Query("MATCH ( user:User { sessionId: {0}} )-[r:VISITED]->(url:Url) "
//            + "RETURN user.sessionId AS sessionId, url.value AS url, url.sesamid AS sesamid, url.mediatype AS mediatype, id(url) AS graphId "
//            + "ORDER BY r.timestamp ASC")
//    Page<Activity> getActivities(String sessionId, Pageable pageable);
//
//    @Query("MATCH ( user:User { sessionId: {0}} )-[r:VISITED]->(url:Url) WHERE id(url) = toInt({1}) RETURN DISTINCT url.value AS url, user.sessionId AS sessionId, url.sesamid AS sesamid, url.mediatype AS mediatype, id(url) AS graphId ")
//    Activity getActivity(String id, String activityId);
}

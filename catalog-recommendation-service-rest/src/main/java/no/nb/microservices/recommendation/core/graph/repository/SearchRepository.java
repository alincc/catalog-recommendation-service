package no.nb.microservices.recommendation.core.graph.repository;

import no.nb.microservices.recommendation.core.graph.model.node.SearchNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface SearchRepository extends GraphRepository<SearchNode> {

    @Query("MATCH (:Session{sessionId:{1})--(search:Search)-[:HAS_QUERY]->(:SearchQuery{query: {0}}) RETURN search")
    SearchNode findSearchByQuery(String query, String sessionId);
//
//    List<Item> findAllByMediatype(String mediatype);

//    @Query("MATCH (url:Url) RETURN url.value AS value, url.sesamid AS sesamid, url.mediatype AS mediatype")
//    Page<NbUrl> getAllUrls(Pageable pageable);
//
//    @Query("MATCH (url:Url {sesamid: {0}})<-[:VISITED]-(:User)-[r:VISITED]->(recommend:Url) "
//            + "WHERE recommend.mediatype = url.mediatype AND NOT (recommend.sesamid = {0}) "
//            + "WITH recommend, COUNT(r) AS count ORDER BY count DESC "
//            + "RETURN DISTINCT(recommend.value) AS value, recommend.mediatype AS mediatype, recommend.sesamid AS sesamid")
//    Page<NbUrl> getRecommendation(String sesamid, Pageable pageable);
//
//    @Query("MATCH (url:Url)<-[r:VISITED]-(:User) "
//            + "WHERE url.mediatype = {0} "
//            + "WITH url, COUNT(r) AS count ORDER BY count DESC "
//            + "RETURN DISTINCT(url.value) AS value, url.sesamid AS sesamid, url.mediatype AS mediatype")
//    Page<NbUrl> getMostPopular(String mediatype, Pageable pageable);
//
//    @Query("MATCH (url:Url)<-[r:VISITED]-(:User) "
//            + "WITH url, COUNT(r) AS count ORDER BY count DESC "
//            + "RETURN DISTINCT(url.value) AS value, url.sesamid AS sesamid, url.mediatype AS mediatype")
//    Page<NbUrl> getMostPopular(Pageable pageable);
//
//
//    @Query("MATCH (url:Url {sesamid: {0}})<-[:VISITED]-(:User)-[r:VISITED]->(recommend:Url) "
//            + "WHERE recommend.mediatype = {1} AND NOT (recommend.sesamid = {0}) "
//            + "WITH recommend, COUNT(r) AS count ORDER BY count DESC "
//            + "RETURN DISTINCT(recommend.value) AS value, recommend.mediatype AS mediatype, recommend.sesamid AS sesamid LIMIT {2}")
//    List<NbUrl> recommend(String sesamid, String mediatype, int count);
//
//    @Query("MATCH (urls:Url)<-[r:VISITED]-(:User) "
//            + "WHERE urls.mediatype = {0} "
//            + "RETURN urls.value AS url, urls.sesamid AS sesamid, COUNT(r) AS count ORDER BY COUNT(r) DESC LIMIT {1}")
//    List<TopVisitedUrl> getTopVisitedUrls(String mediatype, int count);
//
//    @Query("MATCH (url:Url {sesamid: {1}})<-[:VISITED]-(user:User)-[r:VISITED]->(recommend:Url) "
//            + "WHERE NOT user.sessionId = {0} "
//            + "AND NOT (recommend.sesamid = {1}) "
//            + "AND recommend.mediatype = {2} "
//            + "WITH recommend, COUNT(r) AS count ORDER BY count DESC "
//            + "RETURN DISTINCT(recommend.value) AS value, recommend.mediatype AS mediatype, recommend.sesamid AS sesamid LIMIT {3}")
//    List<NbUrl> recommend(String sessionId, String sesamid, String mediatype, int count);
//
//    /**
//     * TESTS
//     */
//    @Query("MATCH (url:Url)<-[r:VISITED]-(:User) "
//            + "WHERE url.mediatype = {0} "
//            + "WITH url, r ORDER BY r.timestamp DESC "
//            + "RETURN url.value AS value, url.sesamid AS sesamid, url.mediatype AS mediatype LIMIT {1}")
//    List<NbUrl> getLastVisitedUrls(String mediatype, int count);
//
//    @Query("MATCH (url:Url)<-[r:VISITED]-(:User) "
//            + "WHERE url.mediatype = {0} AND (r.timestamp > {1}) "
//            + "WITH url, COUNT(r) AS count ORDER BY count DESC "
//            + "RETURN url.value AS value, url.sesamid AS sesamid, url.mediatype AS mediatype LIMIT {2}")
//    List<NbUrl> getTopVisitedUrlsSince(String mediatype, Long time, int count);
}

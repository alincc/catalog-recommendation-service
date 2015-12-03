package no.nb.microservices.clickstream.core.graph.repository;


import no.nb.microservices.clickstream.core.graph.model.relation.Visited;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

//public interface IVisitedRepository extends GraphRepository<Visited> {
//
//    @Query("MATCH ()-[r:VISITED]->() RETURN COUNT(r)")
//    long count();
//
//}

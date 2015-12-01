package no.nb.microservices.clickstream.core.graph.repository;


import no.nb.microservices.clickstream.model.Visited;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface IVisitedRepository extends GraphRepository<Visited> {

    @Override
    @Query("MATCH ()-[r:VISITED]->() RETURN COUNT(r)")
    long count();

}

package no.nb.microservices.recommendation.core.graph.repository;

import no.nb.microservices.recommendation.core.graph.model.node.PublisherNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PublisherRepository extends GraphRepository<PublisherNode> {

    @Query("MERGE (p:Publisher { name: {0}.name }) RETURN p")
    PublisherNode merge(PublisherNode location);

    PublisherNode findByName(String name);

}

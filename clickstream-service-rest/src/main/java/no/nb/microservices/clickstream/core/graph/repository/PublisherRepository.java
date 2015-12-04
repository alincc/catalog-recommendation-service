package no.nb.microservices.clickstream.core.graph.repository;

import no.nb.microservices.clickstream.core.graph.model.node.Location;
import no.nb.microservices.clickstream.core.graph.model.node.Publisher;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PublisherRepository extends GraphRepository<Publisher> {

    @Query("MERGE (p:Publisher { name: {0}.name }) RETURN p")
    Publisher merge(Publisher location);

}

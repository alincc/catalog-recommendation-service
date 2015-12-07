package no.nb.microservices.clickstream.core.graph.repository;

import no.nb.microservices.clickstream.core.graph.model.node.Location;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface LocationRepository extends GraphRepository<Location> {

    @Query("MERGE (l:Location { municipality: {0}.municipality, county: {0}.county, country: {0}.country }) RETURN l")
    Location merge(Location location);

}

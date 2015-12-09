package no.nb.microservices.recommendation.core.graph.repository;

import no.nb.microservices.recommendation.core.graph.model.node.LocationNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface LocationRepository extends GraphRepository<LocationNode> {

    LocationNode findByMunicipalityAndCounty(String municipality, String county);

    @Query("MERGE (l:Location { municipality: {0}.municipality, county: {0}.county, country: {0}.country }) RETURN l")
    LocationNode merge(LocationNode location);

}

package no.nb.microservices.recommendation.core.graph.repository;

import no.nb.microservices.recommendation.core.graph.model.node.MunicipalityNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface MunicipalityRepository extends GraphRepository<MunicipalityNode> {

    @Query("match (n:Municipality)\n" +
            "using index n:Municipality(municipality)\n" +
            "where n.municipality = {0}\n" +
            "return n")
    MunicipalityNode findByMunicipality(String municipality);

}

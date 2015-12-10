package no.nb.microservices.recommendation.core.graph.repository;

import no.nb.microservices.recommendation.core.graph.model.node.CountyNode;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CountyRepository extends GraphRepository<CountyNode> {

    CountyNode findByCounty(String county);

}

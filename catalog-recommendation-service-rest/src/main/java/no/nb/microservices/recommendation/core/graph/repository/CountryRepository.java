package no.nb.microservices.recommendation.core.graph.repository;

import no.nb.microservices.recommendation.core.graph.model.node.CountryNode;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CountryRepository extends GraphRepository<CountryNode> {

    CountryNode findByCountry(String country);

}

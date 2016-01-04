package no.nb.microservices.recommendation.core.graph.repository;

import no.nb.microservices.recommendation.core.graph.model.node.PersonNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PersonRepository extends GraphRepository<PersonNode> {

    @Query("match (n:Person)\n" +
            "using index n:Person(name)\n" +
            "where n.name = {0}\n" +
            "return n")
    PersonNode findByName(String name);

}

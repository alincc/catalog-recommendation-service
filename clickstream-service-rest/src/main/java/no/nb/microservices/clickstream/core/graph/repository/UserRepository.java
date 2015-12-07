package no.nb.microservices.clickstream.core.graph.repository;

import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
import no.nb.microservices.clickstream.core.graph.model.node.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User> {
    User findByUserId(String userId);

    @Query("MERGE (u:User { userId: {0}.userId, age: {0}.age, gender: {0}.gender, interests: {0}.interests }) RETURN u")
    User merge(User user);

}

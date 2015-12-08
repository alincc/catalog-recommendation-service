package no.nb.microservices.clickstream.core.graph.repository;

import no.nb.microservices.clickstream.core.graph.model.node.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<UserNode> {
    UserNode findByUserId(String userId);

    @Query("MERGE (u:User { userId: {0}.userId, age: {0}.age, gender: {0}.gender, interests: {0}.interests }) RETURN u")
    UserNode merge(UserNode user);

}

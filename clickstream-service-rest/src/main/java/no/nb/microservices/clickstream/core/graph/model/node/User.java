package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User {

    private Long id;
    private String userId;

    @Relationship(type = "CREATED", direction = Relationship.OUTGOING)
    private Set<Session> sessionNodes = new HashSet<>();

    protected User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public void addSession(Session session) {
        sessionNodes.add(session);
    }

    public String getUserId() {
        return userId;
    }
}

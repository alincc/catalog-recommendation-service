package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User {

    private Long id;
    private String userId;
    private int age;
    private String gender;
    private Collection<String> interests;

    @Relationship(type = "CREATED", direction = Relationship.OUTGOING)
    private Set<Session> sessionNodes = new HashSet<>();

    protected User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, int age, String gender, Collection<String> interests) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
    }

    public void addSession(Session session) {
        sessionNodes.add(session);
    }

    public String getUserId() {
        return userId;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Collection<String> getInterests() {
        return interests;
    }
}

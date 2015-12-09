package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "User")
public class UserNode {

    private Long id;
    private String userId;
    private int age;
    private String gender;
    private Collection<String> interests;

    @Relationship(type = "CREATED", direction = Relationship.OUTGOING)
    private Set<SessionNode> sessionNodes = new HashSet<>();

    protected UserNode() {
    }

    public UserNode(String userId) {
        this.userId = userId;
    }

    public UserNode(String userId, int age, String gender, Collection<String> interests) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
    }

    public void addSession(SessionNode session) {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInterests(Collection<String> interests) {
        this.interests = interests;
    }

    public Set<SessionNode> getSessionNodes() {
        return sessionNodes;
    }

    public void setSessionNodes(Set<SessionNode> sessionNodes) {
        this.sessionNodes = sessionNodes;
    }
}

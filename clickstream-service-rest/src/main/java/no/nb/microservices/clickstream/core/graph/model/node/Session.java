package no.nb.microservices.clickstream.core.graph.model.node;

import no.nb.microservices.clickstream.core.graph.model.relation.Visited;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity(label = "Session")
public class Session {

    @GraphId
    private long id;

    @Property
    private String sessionId;

    @Relationship(type = "VISITED", direction = Relationship.OUTGOING)
    private Set<Visited> visits;

    @Relationship(type = "CREATED", direction = Relationship.INCOMING)
    private Set<User> userNodes;

    protected Session() {
    }

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public void addUser(User user) {
        userNodes.add(user);
    }

    public void addVisit(Item itemNode) {
        visits.add(new Visited(this, itemNode));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Set<Visited> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visited> visits) {
        this.visits = visits;
    }

    public Set<User> getUserNodes() {
        return userNodes;
    }

    public void setUserNodes(Set<User> userNodes) {
        this.userNodes = userNodes;
    }
}

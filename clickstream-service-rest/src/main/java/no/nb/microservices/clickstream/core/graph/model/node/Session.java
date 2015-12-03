package no.nb.microservices.clickstream.core.graph.model.node;

import no.nb.microservices.clickstream.core.graph.model.relation.Downloaded;
import no.nb.microservices.clickstream.core.graph.model.relation.Liked;
import no.nb.microservices.clickstream.core.graph.model.relation.Visited;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Session {

    @GraphId
    private Long id;

    @Property
    private String sessionId;

    @Relationship(type = "VISITED", direction = Relationship.OUTGOING)
    private Set<Visited> visits = new HashSet<>();

    @Relationship(type = "DOWNLOADED", direction = Relationship.OUTGOING)
    private Set<Downloaded> downloads = new HashSet<>();

    @Relationship(type = "LIKED", direction = Relationship.OUTGOING)
    private Set<Liked> likes = new HashSet<>();

    @Relationship(type = "CREATED", direction = Relationship.INCOMING)
    private Set<User> userNodes = new HashSet<>();

    protected Session() {
    }

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public void addUser(User user) {
        userNodes.add(user);
    }

    public void addAction(Item itemNode, String action) {
        if ("DOWNLOADED".equalsIgnoreCase(action)) {
            downloads.add(new Downloaded(this, itemNode, new Date()));
        }
        else if ("LIKED".equalsIgnoreCase(action)) {
            likes.add(new Liked(this, itemNode, new Date()));
        }
        else {
            visits.add(new Visited(this, itemNode, new Date()));
        }

    }
}

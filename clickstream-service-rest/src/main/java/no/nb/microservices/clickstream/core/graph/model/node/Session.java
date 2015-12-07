package no.nb.microservices.clickstream.core.graph.model.node;

import no.nb.microservices.clickstream.core.graph.model.edge.Downloaded;
import no.nb.microservices.clickstream.core.graph.model.edge.Liked;
import no.nb.microservices.clickstream.core.graph.model.edge.Visited;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Session {

    private Long id;
    private String sessionId;

    @Relationship(type = "VISITED", direction = Relationship.OUTGOING)
    private Set<Visited> visits = new HashSet<>();

    @Relationship(type = "DOWNLOADED", direction = Relationship.OUTGOING)
    private Set<Downloaded> downloads = new HashSet<>();

    @Relationship(type = "LIKED", direction = Relationship.OUTGOING)
    private Set<Liked> likes = new HashSet<>();

    @Relationship(type = "CREATED_SEARCH", direction = Relationship.OUTGOING)
    private Set<Search> searches = new HashSet<>();

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private Location location;

    protected Session() {
    }

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public Session(String sessionId, Location location) {
        this.sessionId = sessionId;
        this.location = location;
    }

    public void addAction(Item itemNode, String action) {
        if ("DOWNLOADED".equalsIgnoreCase(action)) {
            downloads.add(new Downloaded(this, itemNode, new Date()));
        } else if ("LIKED".equalsIgnoreCase(action)) {
            likes.add(new Liked(this, itemNode, new Date()));
        } else {
            visits.add(new Visited(this, itemNode, new Date()));
        }

    }

    public void addSearch(Search search) {
        searches.add(search);
    }

    public String getSessionId() {
        return sessionId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

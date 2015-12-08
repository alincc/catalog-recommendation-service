package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Session")
public class SessionNode {

    private Long id;
    private String sessionId;

    @Relationship(type = "VISITED", direction = Relationship.OUTGOING)
    private Set<ItemNode> visits = new HashSet<>();

    @Relationship(type = "DOWNLOADED", direction = Relationship.OUTGOING)
    private Set<ItemNode> downloads = new HashSet<>();

    @Relationship(type = "LIKED", direction = Relationship.OUTGOING)
    private Set<ItemNode> likes = new HashSet<>();

    @Relationship(type = "CREATED_SEARCH", direction = Relationship.OUTGOING)
    private Set<SearchNode> searches = new HashSet<>();

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private LocationNode location;

    protected SessionNode() {
    }

    public SessionNode(String sessionId) {
        this.sessionId = sessionId;
    }

    public SessionNode(String sessionId, LocationNode location) {
        this.sessionId = sessionId;
        this.location = location;
    }

    public void addAction(ItemNode itemNode, String action) {
        if ("DOWNLOADED".equalsIgnoreCase(action)) {
            downloads.add(itemNode);
        } else if ("LIKED".equalsIgnoreCase(action)) {
            likes.add(itemNode);
        } else {
            visits.add(itemNode);
        }

    }

    public void addSearch(SearchNode search) {
        searches.add(search);
    }

    public String getSessionId() {
        return sessionId;
    }

    public LocationNode getLocation() {
        return location;
    }

    public void setLocation(LocationNode location) {
        this.location = location;
    }

    public Set<SearchNode> getSearches() {
        return searches;
    }

    public void setSearches(Set<SearchNode> searches) {
        this.searches = searches;
    }
}

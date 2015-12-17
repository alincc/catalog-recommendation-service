package no.nb.microservices.recommendation.core.graph.model.node;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Session")
public class SessionNode {

    private Long id;

    @Index(unique = true)
    private String sessionId;

    @Relationship(type = "VISITED", direction = Relationship.OUTGOING)
    private Set<ItemNode> visits = new HashSet<>();

    @Relationship(type = "DOWNLOADED", direction = Relationship.OUTGOING)
    private Set<ItemNode> downloads = new HashSet<>();

    @Relationship(type = "LIKES", direction = Relationship.OUTGOING)
    private Set<ItemNode> likes = new HashSet<>();

    @Relationship(type = "CREATED_SEARCH", direction = Relationship.OUTGOING)
    private Set<SearchNode> searches = new HashSet<>();

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private MunicipalityNode location;

    @DateLong
    private Date date;

    protected SessionNode() {
    }

    public SessionNode(String sessionId) {
        this.sessionId = sessionId;
    }

    public SessionNode(String sessionId, Date date) {
        this.sessionId = sessionId;
        this.date = date;
    }

    public SessionNode(String sessionId, MunicipalityNode location) {
        this.sessionId = sessionId;
        this.location = location;
    }

    public SessionNode(String sessionId, Date date, MunicipalityNode locationNode) {
        this.sessionId = sessionId;
        this.date = date;
        this.location = locationNode;
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
        if (search != null) {
            searches.add(search);
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public MunicipalityNode getLocation() {
        return location;
    }

    public void setLocation(MunicipalityNode location) {
        this.location = location;
    }

    public Set<SearchNode> getSearches() {
        return searches;
    }

    public void setSearches(Set<SearchNode> searches) {
        this.searches = searches;
    }
}

package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Search {

    private Long id;
    private String query;

    @Relationship(type = "VISITED", direction = Relationship.OUTGOING)
    private Set<Item> visits = new HashSet<>();

    @Relationship(type = "DOWNLOADED", direction = Relationship.OUTGOING)
    private Set<Item> downloads = new HashSet<>();

    @Relationship(type = "LIKED", direction = Relationship.OUTGOING)
    private Set<Item> likes = new HashSet<>();

    public Search() {
    }

    public Search(String query) {
        this.query = query;
    }

    public void addAction(Item item, String action) {
        if ("DOWNLOADED".equalsIgnoreCase(action)) {
            downloads.add(item);
        }
        else if ("LIKED".equalsIgnoreCase(action)) {
            likes.add(item);
        }
        else {
            visits.add(item);
        }
    }

    public String getQuery() {
        return query;
    }
}

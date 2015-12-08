package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Search")
public class SearchNode {

    private Long id;

    @Relationship(type = "VISITED_AFTER_SEARCH", direction = Relationship.OUTGOING)
    private Set<ItemNode> visits = new HashSet<>();

    @Relationship(type = "DOWNLOADED_AFTER_SEARCH", direction = Relationship.OUTGOING)
    private Set<ItemNode> downloads = new HashSet<>();

    @Relationship(type = "LIKED_AFTER_SEARCH", direction = Relationship.OUTGOING)
    private Set<ItemNode> likes = new HashSet<>();

    @Relationship(type = "HAS_QUERY", direction = Relationship.OUTGOING)
    private SearchQueryNode searchQuery;

    public SearchNode() {
    }

    public SearchNode(SearchQueryNode searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void addAction(ItemNode item, String action) {
        if ("DOWNLOADED".equalsIgnoreCase(action)) {
            downloads.add(item);
        } else if ("LIKED".equalsIgnoreCase(action)) {
            likes.add(item);
        } else {
            visits.add(item);
        }
    }

    public void setSearchQuery(SearchQueryNode searchQuery) {
        this.searchQuery = searchQuery;
    }

    public SearchQueryNode getSearchQuery() {
        return searchQuery;
    }
}

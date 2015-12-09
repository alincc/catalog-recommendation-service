package no.nb.microservices.recommendation.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "SearchQuery")
public class SearchQueryNode {
    private Long id;
    private String query;

    public SearchQueryNode() {
    }

    public SearchQueryNode(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

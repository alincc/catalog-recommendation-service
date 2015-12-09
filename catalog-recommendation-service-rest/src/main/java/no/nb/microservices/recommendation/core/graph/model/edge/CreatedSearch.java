package no.nb.microservices.recommendation.core.graph.model.edge;


import no.nb.microservices.recommendation.core.graph.model.node.SearchNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@RelationshipEntity(type = "CREATED_SEARCH")
public class CreatedSearch {

    @GraphId
    private Long id;

    @StartNode
    private SessionNode session;

    @EndNode
    private SearchNode search;

    @DateLong
    private Date timestamp;

    public CreatedSearch() {

    }

    public CreatedSearch(SessionNode session, SearchNode search, Date timestamp) {
        this.session = session;
        this.search = search;
        this.timestamp = timestamp;
    }
}

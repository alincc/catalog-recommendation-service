package no.nb.microservices.clickstream.core.graph.model.relation;


import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Search;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
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
    private Session session;

    @EndNode
    private Search search;

    @DateLong
    private Date timestamp;

    public CreatedSearch() {

    }

    public CreatedSearch(Session session, Search search, Date timestamp) {
        this.session = session;
        this.search = search;
        this.timestamp = timestamp;
    }
}

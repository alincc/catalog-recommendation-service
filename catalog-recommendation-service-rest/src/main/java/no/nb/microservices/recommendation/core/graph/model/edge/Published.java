package no.nb.microservices.recommendation.core.graph.model.edge;


import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.PublisherNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@RelationshipEntity(type = "PUBLISHED")
public class Published {

    @GraphId
    private Long id;

    @StartNode
    private PublisherNode publisherNode;

    @EndNode
    private ItemNode itemNode;

    @DateLong
    private Date timestamp;

    public Published() {

    }

    public Published(PublisherNode publisherNode, ItemNode itemNode, Date timestamp) {
        this.publisherNode = publisherNode;
        this.itemNode = itemNode;
        this.timestamp = timestamp;
    }
}

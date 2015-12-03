package no.nb.microservices.clickstream.core.graph.model.relation;


import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

public abstract class AbstractItemAction {

    @GraphId
    private Long id;

    @StartNode
    private Session session;

    @EndNode
    private Item item;

    @DateLong
    private Date timestamp;

    public AbstractItemAction() {

    }

    public AbstractItemAction(Session session, Item item, Date timestamp) {
        this.session = session;
        this.item = item;
        this.timestamp = timestamp;
    }
}

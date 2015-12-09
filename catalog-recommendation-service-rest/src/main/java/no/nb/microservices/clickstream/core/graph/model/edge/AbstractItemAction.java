package no.nb.microservices.clickstream.core.graph.model.edge;


import no.nb.microservices.clickstream.core.graph.model.node.ItemNode;
import no.nb.microservices.clickstream.core.graph.model.node.SessionNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

public abstract class AbstractItemAction {

    @GraphId
    private Long id;

    @StartNode
    private SessionNode session;

    @EndNode
    private ItemNode item;

    @DateLong
    private Date timestamp;

    public AbstractItemAction() {

    }

    public AbstractItemAction(SessionNode session, ItemNode item, Date timestamp) {
        this.session = session;
        this.item = item;
        this.timestamp = timestamp;
    }
}

package no.nb.microservices.clickstream.core.graph.model.edge;


import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.Date;

@RelationshipEntity(type = "VISITED")
public class Visited extends AbstractItemAction {

    public Visited() {
        super();
    }

    public Visited(Session session, Item item, Date timestamp) {
        super(session, item, timestamp);
    }
}

package no.nb.microservices.clickstream.core.graph.model.relation;


import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.Date;

@RelationshipEntity(type = "DOWNLOADED")
public class Downloaded extends AbstractItemAction {

    public Downloaded() {
        super();
    }

    public Downloaded(Session session, Item item, Date timestamp) {
        super(session, item, timestamp);
    }
}

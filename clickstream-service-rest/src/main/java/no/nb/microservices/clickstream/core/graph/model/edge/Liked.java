package no.nb.microservices.clickstream.core.graph.model.edge;


import no.nb.microservices.clickstream.core.graph.model.node.ItemNode;
import no.nb.microservices.clickstream.core.graph.model.node.SessionNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.Date;

@RelationshipEntity(type = "LIKES")
public class Liked extends AbstractItemAction {

    public Liked() {
        super();
    }

    public Liked(SessionNode session, ItemNode item, Date timestamp) {
        super(session, item, timestamp);
    }
}

package no.nb.microservices.recommendation.core.graph.model.edge;


import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.Date;

@RelationshipEntity(type = "DOWNLOADED")
public class Downloaded extends AbstractItemAction {

    public Downloaded() {
        super();
    }

    public Downloaded(SessionNode session, ItemNode item, Date timestamp) {
        super(session, item, timestamp);
    }
}

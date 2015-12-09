package no.nb.microservices.recommendation.core.graph.model.edge;


import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.Date;

@RelationshipEntity(type = "VISITED")
public class Visited extends AbstractItemAction {

    public Visited() {
        super();
    }

    public Visited(SessionNode session, ItemNode item, Date timestamp) {
        super(session, item, timestamp);
    }
}

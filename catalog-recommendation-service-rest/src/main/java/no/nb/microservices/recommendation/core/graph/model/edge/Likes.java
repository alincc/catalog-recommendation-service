package no.nb.microservices.recommendation.core.graph.model.edge;


import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.Date;

@RelationshipEntity(type = "LIKES")
public class Likes extends AbstractItemAction {

    public Likes() {
        super();
    }

    public Likes(SessionNode session, ItemNode item, Date timestamp) {
        super(session, item, timestamp);
    }
}

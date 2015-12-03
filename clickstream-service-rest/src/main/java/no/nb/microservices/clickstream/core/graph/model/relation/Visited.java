package no.nb.microservices.clickstream.core.graph.model.relation;


import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@RelationshipEntity(type = "VISITED")
public class Visited {

    @StartNode
    private Session session;

    @EndNode
    private Item item;

    @DateLong
    private Date timestamp;

    public Visited() {

    }

    public Visited(Session session, Item item) {
        this.session = session;
        this.item = item;
    }
}

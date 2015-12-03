package no.nb.microservices.clickstream.core.graph.model.node;

import no.nb.microservices.clickstream.core.graph.model.relation.Visited;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Item {

    @GraphId
    private Long id;

    @Property
    private String itemId;

    @Property
    private String mediatype;

    @Relationship(type = "VISITED", direction = Relationship.INCOMING)
    private Set<Visited> visitors = new HashSet<>();

    public Item() {
    }

    public Item(String itemId, String mediatype) {
        this.itemId = itemId;
        this.mediatype = mediatype;
    }

    public void addVisitor(Session sessionNode) {
        visitors.add(new Visited(sessionNode, this, new Date()));
    }
}

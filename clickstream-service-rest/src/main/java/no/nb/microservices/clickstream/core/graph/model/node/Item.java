package no.nb.microservices.clickstream.core.graph.model.node;

import no.nb.microservices.clickstream.core.graph.model.relation.Visited;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity(label="Item")
public class Item {

    @GraphId
    private long id;

    @Property
    private String itemId;

    @Property
    private String mediatype;

    @Relationship(type = "VISITED", direction = Relationship.INCOMING)
    private Set<Visited> visitors;

    protected Item() {
    }

    public Item(String itemId, String mediatype) {
        this.itemId = itemId;
        this.mediatype = mediatype;
    }

    public void addVisitor(Session sessionNode) {
        visitors.add(new Visited(sessionNode, this));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    public Set<Visited> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visited> visitors) {
        this.visitors = visitors;
    }
}

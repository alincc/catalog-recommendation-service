package no.nb.microservices.clickstream.core.graph.model.node;

import no.nb.microservices.clickstream.core.graph.model.relation.Visited;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.*;

@NodeEntity
public class Item {

    private Long id;
    private String itemId;
    private String mediatype;
    private Collection<String> topics;

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private Location location;

    @Relationship(type = "PUBLISHED_BY", direction = Relationship.OUTGOING)
    private Publisher publisher;

    protected Item() {
    }

    public Item(String itemId, String mediatype, Collection<String> topics) {
        this.itemId = itemId;
        this.mediatype = mediatype;
        this.topics = topics;
    }

    public String getMediatype() {
        return mediatype;
    }

    public String getItemId() {
        return itemId;
    }

    public Collection<String> getTopics() {
        return topics;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}

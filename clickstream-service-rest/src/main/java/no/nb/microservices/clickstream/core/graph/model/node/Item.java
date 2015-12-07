package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.*;

@NodeEntity
public class Item {

    private Long id;
    private String itemId;
    private String mediaType;
    private Collection<String> topics;

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private Location location;

    @Relationship(type = "PUBLISHED_BY", direction = Relationship.OUTGOING)
    private Publisher publisher;

    public Item() {
    }

    public Item(String itemId, String mediaType, Collection<String> topics) {
        this.itemId = itemId;
        this.mediaType = mediaType;
        this.topics = topics;
    }

    public String getMediaType() {
        return mediaType;
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

    public Location getLocation() {
        return location;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setTopics(Collection<String> topics) {
        this.topics = topics;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}

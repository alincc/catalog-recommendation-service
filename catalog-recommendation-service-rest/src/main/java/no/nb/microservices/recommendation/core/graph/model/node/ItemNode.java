package no.nb.microservices.recommendation.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;

@NodeEntity(label = "Item")
public class ItemNode {

    private Long id;
    private String itemId;
    private String mediaType;
    private Collection<String> topics;

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private LocationNode location;

    @Relationship(type = "PUBLISHED_BY", direction = Relationship.OUTGOING)
    private PublisherNode publisher;

    public ItemNode() {
    }

    public ItemNode(String itemId) {
        this.itemId = itemId;
    }

    public ItemNode(String itemId, String mediaType, Collection<String> topics) {
        this.itemId = itemId;
        this.mediaType = mediaType;
        this.topics = topics;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Collection<String> getTopics() {
        return topics;
    }

    public void setTopics(Collection<String> topics) {
        this.topics = topics;
    }

    public LocationNode getLocation() {
        return location;
    }

    public void setLocation(LocationNode location) {
        this.location = location;
    }

    public PublisherNode getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherNode publisher) {
        this.publisher = publisher;
    }
}

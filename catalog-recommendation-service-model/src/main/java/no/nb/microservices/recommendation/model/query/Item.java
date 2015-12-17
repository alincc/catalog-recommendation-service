package no.nb.microservices.recommendation.model.query;

import java.util.Collection;

public class Item {
    private String itemId;
    private String mediaType;
    private String publisher;
    private Location location;
    private Collection<String> topics;

    public Item() {
    }

    public Item(String itemId, String mediaType, Collection<String> topics) {
        this.itemId = itemId;
        this.mediaType = mediaType;
        this.topics = topics;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Collection<String> getTopics() {
        return topics;
    }

    public void setTopics(Collection<String> topics) {
        this.topics = topics;
    }
}

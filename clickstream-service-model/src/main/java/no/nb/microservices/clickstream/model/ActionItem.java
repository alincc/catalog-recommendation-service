package no.nb.microservices.clickstream.model;

public class ActionItem {
    private String userId;
    private String sessionId;
    private String query;
    private String action;
    private String itemId;
    private String mediatype;
    private String publisher;
    private Location itemLocation;
    private Location sessionLocation;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Location getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(Location itemLocation) {
        this.itemLocation = itemLocation;
    }

    public Location getSessionLocation() {
        return sessionLocation;
    }

    public void setSessionLocation(Location sessionLocation) {
        this.sessionLocation = sessionLocation;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

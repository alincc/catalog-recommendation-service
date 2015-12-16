package no.nb.microservices.recommendation.model.response;


import no.nb.microservices.catalogitem.rest.model.ItemResource;

public class Recommendation {
    private double score;
    private String itemId;
    private ItemResource itemResource;

    public Recommendation() {
    }

    public Recommendation(String itemId, double score) {
        this.itemId = itemId;
        this.score = score;
    }

    public Recommendation(ItemResource itemResource, double score) {
        this.itemResource = itemResource;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ItemResource getItemResource() {
        return itemResource;
    }

    public void setItemResource(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


}

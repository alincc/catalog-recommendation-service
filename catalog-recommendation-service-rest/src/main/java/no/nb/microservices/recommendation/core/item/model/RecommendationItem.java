package no.nb.microservices.recommendation.core.item.model;

import no.nb.microservices.catalogitem.rest.model.ItemResource;

public class RecommendationItem {
    private double score;
    private ItemResource itemResource;

    public RecommendationItem(double score, ItemResource itemResource) {
        this.score = score;
        this.itemResource = itemResource;
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
}

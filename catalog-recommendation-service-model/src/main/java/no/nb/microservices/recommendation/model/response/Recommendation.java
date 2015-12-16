package no.nb.microservices.recommendation.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import no.nb.microservices.catalogitem.rest.model.ItemResource;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recommendation {
    private double score;
    private ItemResource itemResource;

    public Recommendation() {
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
}

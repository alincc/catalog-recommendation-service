package no.nb.microservices.recommendation.core.item.model;

import no.nb.microservices.catalogitem.rest.model.ItemResource;
import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;

import java.util.concurrent.Future;

public class RecommendationFuture {
    private RecommendationQuery recommendationQuery;
    private Future<ItemResource> itemResourceFuture;

    public RecommendationFuture(RecommendationQuery recommendationQuery, Future<ItemResource> itemResourceFuture) {
        this.recommendationQuery = recommendationQuery;
        this.itemResourceFuture = itemResourceFuture;
    }

    public RecommendationQuery getRecommendationQuery() {
        return recommendationQuery;
    }

    public void setRecommendationQuery(RecommendationQuery recommendationQuery) {
        this.recommendationQuery = recommendationQuery;
    }

    public Future<ItemResource> getItemResourceFuture() {
        return itemResourceFuture;
    }

    public void setItemResourceFuture(Future<ItemResource> itemResourceFuture) {
        this.itemResourceFuture = itemResourceFuture;
    }
}

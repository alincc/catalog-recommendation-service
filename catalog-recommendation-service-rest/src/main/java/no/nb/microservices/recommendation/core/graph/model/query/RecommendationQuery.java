package no.nb.microservices.recommendation.core.graph.model.query;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class RecommendationQuery {
    private String itemId;
    private double score;

    public RecommendationQuery() {
    }

    public RecommendationQuery(String itemId, double score) {
        this.itemId = itemId;
        this.score = score;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

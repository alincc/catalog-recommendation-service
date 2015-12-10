package no.nb.microservices.recommendation.core.graph.model.query;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class RecommendationQuery {
    private String itemId;
    private double score;

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

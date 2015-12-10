package no.nb.microservices.recommendation.model;

import java.util.List;

public class RecommendationWrapper {
    private List<Recommendation> recommendations;

    public RecommendationWrapper() {
    }

    public RecommendationWrapper(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}

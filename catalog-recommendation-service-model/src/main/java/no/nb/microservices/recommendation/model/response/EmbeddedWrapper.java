package no.nb.microservices.recommendation.model.response;

import java.util.List;

public class EmbeddedWrapper {

    private List<Recommendation> recommendations;

    public EmbeddedWrapper() {
    }

    public EmbeddedWrapper(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}

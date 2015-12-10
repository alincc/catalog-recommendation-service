package no.nb.microservices.recommendation.rest.assembler;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.model.Recommendation;
import no.nb.microservices.recommendation.model.RecommendationWrapper;

import java.util.Collection;
import java.util.stream.Collectors;

public class RecommendationAssembler {

    private Collection<RecommendationQuery> recommendationQueries;

    public RecommendationAssembler(Collection<RecommendationQuery> recommendationQueries) {
        this.recommendationQueries = recommendationQueries;
    }

    public RecommendationWrapper build() {
        return new RecommendationWrapper(recommendationQueries.stream().map(q -> map(q)).collect(Collectors.toList()));
    }

    private Recommendation map(RecommendationQuery recommendationQuery) {
        return new Recommendation(recommendationQuery.getItemId(), recommendationQuery.getScore());
    }
}

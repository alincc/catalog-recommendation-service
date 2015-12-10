package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.model.Recommendation;

import java.util.Collection;

public interface GraphQueryService {
    Collection<RecommendationQuery> findWhatOtherHaveVisited(String itemId);
}

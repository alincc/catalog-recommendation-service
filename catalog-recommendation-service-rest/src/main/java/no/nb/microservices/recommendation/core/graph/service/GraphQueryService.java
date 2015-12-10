package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;

import java.util.Collection;

public interface GraphQueryService {
    Collection<RecommendationQuery> findWhatOtherHaveVisited(String itemId);
    Collection<RecommendationQuery> findMostVisited(long fromDate, long toDate, int limit);
}

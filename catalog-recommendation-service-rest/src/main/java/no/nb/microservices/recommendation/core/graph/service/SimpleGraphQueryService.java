package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.model.Recommendation;

import java.util.Collection;

public class SimpleGraphQueryService implements GraphQueryService {
    @Override
    public Collection<Recommendation> findWhatOtherHaveVisited(String itemId) {
        return null;
    }
}

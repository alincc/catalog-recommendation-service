package no.nb.microservices.recommendation.core.filter;

import no.nb.microservices.recommendation.core.item.model.RecommendationItem;

import java.util.Collection;

public class SimplePermissionFilter implements PermissionFilter {
    @Override
    public Collection<RecommendationItem> filter(Collection<RecommendationItem> recommendationItems) {
        return recommendationItems;
    }
}

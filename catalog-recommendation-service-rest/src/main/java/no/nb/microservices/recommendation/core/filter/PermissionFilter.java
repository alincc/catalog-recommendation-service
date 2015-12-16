package no.nb.microservices.recommendation.core.filter;

import no.nb.microservices.recommendation.core.item.model.RecommendationItem;

import java.util.Collection;

public interface PermissionFilter {
    Collection<RecommendationItem> filter(Collection<RecommendationItem> recommendationItems);
}

package no.nb.microservices.recommendation.core.filter;

import no.nb.microservices.recommendation.core.item.model.RecommendationItem;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SimplePermissionFilter implements PermissionFilter {
    @Override
    public Collection<RecommendationItem> filter(Collection<RecommendationItem> recommendationItems) {
        // TODO: Write filter
        return recommendationItems;
    }
}

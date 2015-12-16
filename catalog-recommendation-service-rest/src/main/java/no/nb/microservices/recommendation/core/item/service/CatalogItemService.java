package no.nb.microservices.recommendation.core.item.service;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.item.model.RecommendationItem;

import java.util.Collection;

public interface CatalogItemService {

    Collection<RecommendationItem> appendItems(Collection<RecommendationQuery> recommendationQueryCollection);
    
}

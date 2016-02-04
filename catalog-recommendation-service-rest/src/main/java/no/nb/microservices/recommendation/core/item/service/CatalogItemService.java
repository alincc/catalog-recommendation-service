package no.nb.microservices.recommendation.core.item.service;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.item.model.RecommendationItem;

import java.util.Collection;
import java.util.List;

public interface CatalogItemService {

    Collection<RecommendationItem> appendItems(Collection<RecommendationQuery> recommendationQueryCollection, List<String> expand, List<String> fields);
}

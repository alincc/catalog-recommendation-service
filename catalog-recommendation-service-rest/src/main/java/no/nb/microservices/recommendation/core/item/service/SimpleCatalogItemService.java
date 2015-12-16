package no.nb.microservices.recommendation.core.item.service;

import no.nb.microservices.catalogitem.rest.model.ItemResource;
import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.item.model.RecommendationFuture;
import no.nb.microservices.recommendation.core.item.model.RecommendationItem;
import no.nb.microservices.recommendation.core.item.repository.CatalogItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleCatalogItemService implements CatalogItemService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleCatalogItemService.class);
    private final CatalogItemRepository catalogItemRepository;

    @Autowired
    public SimpleCatalogItemService(CatalogItemRepository catalogItemRepository) {
        this.catalogItemRepository = catalogItemRepository;
    }

    @Override
    public Collection<RecommendationItem> appendItems(Collection<RecommendationQuery> recommendationQueryCollection) {
        List<RecommendationFuture> futureList = recommendationQueryCollection.stream().map(query -> getItemResource(query, null, null)).collect(Collectors.toList());

        List<RecommendationItem> recommendationItems = futureList.stream().map(q -> map(q)).collect(Collectors.toList());

        return recommendationItems;
    }

    private RecommendationItem map(RecommendationFuture recommendationFuture) {
        try {
            return new RecommendationItem(
                    recommendationFuture.getRecommendationQuery().getScore(),
                    recommendationFuture.getItemResourceFuture().get());
        } catch (Exception e) {
            LOG.error("Failed to get async result", e);
            return new RecommendationItem(0, null);
        }
    }

    @Async
    private RecommendationFuture getItemResource(RecommendationQuery recommendationQuery, List<String> expand, List<String> fields) {
        ItemResource itemResource = catalogItemRepository.getItem(recommendationQuery.getItemId(), fields, expand);
        AsyncResult<ItemResource> asyncResult = new AsyncResult<>(itemResource);

        if (itemResource == null) {
            asyncResult.cancel(true);
        }

        return new RecommendationFuture(recommendationQuery, asyncResult);
    }

}
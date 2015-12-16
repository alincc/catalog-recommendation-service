package no.nb.microservices.recommendation.rest.assembler;

import no.nb.microservices.recommendation.core.filter.PermissionFilter;
import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.item.model.RecommendationItem;
import no.nb.microservices.recommendation.core.item.service.CatalogItemService;
import no.nb.microservices.recommendation.model.response.Recommendation;
import no.nb.microservices.recommendation.model.response.RecommendationWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationAssembler {

    private Collection<RecommendationQuery> recommendationQueries;
    private CatalogItemService catalogItemService;
    private PermissionFilter permissionFilter;
    private boolean filterItems = false;
    private boolean addItems = false;


    public RecommendationAssembler(Collection<RecommendationQuery> recommendationQueries) {
        this.recommendationQueries = recommendationQueries;
    }

    public RecommendationAssembler withFilter(PermissionFilter permissionFilter) {
        this.permissionFilter = permissionFilter;
        this.filterItems = true;
        return this;
    }

    public RecommendationAssembler appendItem(CatalogItemService catalogItemService) {
        this.catalogItemService = catalogItemService;
        this.addItems = true;
        return this;
    }

    public RecommendationWrapper build() {
        if (this.addItems) {
            Collection<RecommendationItem> recommendationItems = catalogItemService.appendItems(recommendationQueries);

            if (this.filterItems) {
                recommendationItems = permissionFilter.filter(recommendationItems);
            }

            return new RecommendationWrapper(recommendationItems.stream().map(q -> mapItem(q)).collect(Collectors.toList()));
        }
        else {
            return new RecommendationWrapper(recommendationQueries.stream().map(q -> mapQuery(q)).collect(Collectors.toList()));

        }
    }

    private Recommendation mapItem(RecommendationItem recommendationItem) {
        return new Recommendation(recommendationItem.getItemResource(), recommendationItem.getScore());
    }

    private Recommendation mapQuery(RecommendationQuery recommendationQuery) {
        return new Recommendation(recommendationQuery.getItemId(), recommendationQuery.getScore());
    }

}

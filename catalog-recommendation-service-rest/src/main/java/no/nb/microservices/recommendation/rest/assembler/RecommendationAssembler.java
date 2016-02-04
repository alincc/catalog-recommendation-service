package no.nb.microservices.recommendation.rest.assembler;

import no.nb.microservices.catalogitem.rest.model.ItemResource;
import no.nb.microservices.recommendation.core.filter.PermissionFilter;
import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.item.model.RecommendationItem;
import no.nb.microservices.recommendation.core.item.service.CatalogItemService;
import no.nb.microservices.recommendation.model.response.EmbeddedWrapper;
import no.nb.microservices.recommendation.model.response.Recommendation;
import no.nb.microservices.recommendation.model.response.RootResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationAssembler {

    private Collection<RecommendationQuery> recommendationQueries;
    private CatalogItemService catalogItemService;
    private PermissionFilter permissionFilter;
    private boolean filterItems = false;
    private boolean addItems = false;
    private List<String> expand;
    private List<String> fields;


    public RecommendationAssembler(Collection<RecommendationQuery> recommendationQueries) {
        this.recommendationQueries = recommendationQueries;
    }

    public RecommendationAssembler withFilter(PermissionFilter permissionFilter) {
        this.permissionFilter = permissionFilter;
        this.filterItems = (this.permissionFilter != null);
        return this;
    }

    public RecommendationAssembler appendItem(CatalogItemService catalogItemService, List<String> expand, List<String> fields) {
        this.expand = expand;
        this.fields = fields;
        this.catalogItemService = catalogItemService;
        this.addItems = (this.catalogItemService != null);
        return this;
    }

    public RootResponse build() {
        List<Recommendation> recommendations;

        if (this.addItems) {
            Collection<RecommendationItem> recommendationItems = catalogItemService.appendItems(recommendationQueries, expand, fields);

            if (this.filterItems) {
                recommendationItems = permissionFilter.filter(recommendationItems);
            }
            recommendations = recommendationItems.stream().map(q -> mapItem(q)).collect(Collectors.toList());
        }
        else {
            recommendations = recommendationQueries.stream().map(q -> mapQuery(q)).collect(Collectors.toList());

        }

        RootResponse rootResponse = new RootResponse(new EmbeddedWrapper(recommendations));

        return rootResponse;
    }

    private Recommendation mapItem(RecommendationItem recommendationItem) {
        return new Recommendation(recommendationItem.getItemResource(), recommendationItem.getScore());
    }

    private Recommendation mapQuery(RecommendationQuery recommendationQuery) {
        ItemResource itemResource = new ItemResource(recommendationQuery.getItemId());

        itemResource.add(ResourceLinkBuilder.linkTo(ResourceTemplateLink.ITEM_SELF, recommendationQuery.getItemId()).withRel("self"));

        return new Recommendation(itemResource, recommendationQuery.getScore());
    }

}

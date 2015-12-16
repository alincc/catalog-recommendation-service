package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.filter.PermissionFilter;
import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.core.item.service.CatalogItemService;
import no.nb.microservices.recommendation.model.response.RecommendationWrapper;
import no.nb.microservices.recommendation.rest.assembler.RecommendationAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/v1/catalog/recommend")
public class QueryController {

    private final GraphQueryService graphQueryService;
    private final CatalogItemService catalogItemService;
    private final PermissionFilter permissionFilter;

    @Autowired
    public QueryController(GraphQueryService graphQueryService, CatalogItemService catalogItemService, PermissionFilter permissionFilter) {
        this.graphQueryService = graphQueryService;
        this.catalogItemService = catalogItemService;
        this.permissionFilter = permissionFilter;
    }

    @RequestMapping(value = "/query/othershavevisited")
    public RecommendationWrapper findOtherHaveVisited(@RequestParam String itemId) {
        Collection<RecommendationQuery> whatOtherHaveVisited = graphQueryService.findWhatOtherHaveVisited(itemId);

        return new RecommendationAssembler(whatOtherHaveVisited)
                .appendItem(catalogItemService)
                .withFilter(permissionFilter)
                .build();
    }

    @RequestMapping(value = "/query/mostvisited")
    public RecommendationWrapper findMostVisitedItems(@RequestParam(required = false, defaultValue = "01010001") @DateTimeFormat(pattern = "MMddyyyy") Date fromDate,
                                                      @RequestParam(required = false, defaultValue = "01012999") @DateTimeFormat(pattern = "MMddyyyy") Date toDate,
                                                      @RequestParam(required = false, defaultValue = "10") int limit,
                                                      @RequestParam(required = false) String mediaType)
    {
        Collection<RecommendationQuery> mostVisitedItems = graphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), limit, mediaType);

        return new RecommendationAssembler(mostVisitedItems)
                .appendItem(catalogItemService)
                .withFilter(permissionFilter)
                .build();
    }
}

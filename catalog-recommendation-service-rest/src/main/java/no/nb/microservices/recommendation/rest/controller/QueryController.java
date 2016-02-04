package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.filter.PermissionFilter;
import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.core.item.service.CatalogItemService;
import no.nb.microservices.recommendation.model.response.RootResponse;
import no.nb.microservices.recommendation.rest.assembler.RecommendationAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/catalog/v1/recommend/query")
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

    @RequestMapping(value = "/othershavevisited")
    public RootResponse findOtherHaveVisited(@RequestParam String itemId,
                                             @RequestParam(required = false) List<String> expand,
                                             @RequestParam(required = false) List<String> fields,
                                             @RequestParam(required = false) boolean filter) {
        Collection<RecommendationQuery> recommendations = graphQueryService.findWhatOtherHaveVisited(itemId);

        RecommendationAssembler assembler = new RecommendationAssembler(recommendations);
        if (expand != null && expand.contains("item")) assembler.appendItem(catalogItemService, null, fields);
        if (filter) assembler.withFilter(permissionFilter);

        RootResponse root = assembler.build();
        root.add(linkTo(methodOn(QueryController.class).findOtherHaveVisited(itemId, expand, fields, filter)).withSelfRel());
        return root;
    }

    @RequestMapping(value = "/mostvisited")
    public RootResponse findMostVisitedItems(@RequestParam(required = false, defaultValue = "01010001") @DateTimeFormat(pattern = "MMddyyyy") Date fromDate,
                                             @RequestParam(required = false, defaultValue = "01012999") @DateTimeFormat(pattern = "MMddyyyy") Date toDate,
                                             @RequestParam(required = false, defaultValue = "10") int limit,
                                             @RequestParam(required = false) String mediaType,
                                             @RequestParam(required = false) List<String> expand,
                                             @RequestParam(required = false) List<String> fields) {
        Collection<RecommendationQuery> recommendations = graphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), limit, mediaType);

        RecommendationAssembler assembler = new RecommendationAssembler(recommendations);
        if (expand != null && expand.contains("item")) assembler.appendItem(catalogItemService, null, fields);

        RootResponse root = assembler.build();
        root.add(linkTo(methodOn(QueryController.class).findMostVisitedItems(fromDate, toDate, limit, mediaType, expand, fields)).withSelfRel());
        return root;
    }
}

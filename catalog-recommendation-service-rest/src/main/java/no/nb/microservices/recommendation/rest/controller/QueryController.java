package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.model.RecommendationWrapper;
import no.nb.microservices.recommendation.rest.assembler.RecommendationAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/v1/catalog/recommend")
public class QueryController {

    private final GraphQueryService graphQueryService;

    @Autowired
    public QueryController(GraphQueryService graphQueryService) {
        this.graphQueryService = graphQueryService;
    }

    @RequestMapping(value = "/query/othershavevisited")
    public RecommendationWrapper findOtherHaveVisited(@RequestParam String itemId) {
        return new RecommendationAssembler(graphQueryService.findWhatOtherHaveVisited(itemId)).build();
    }

    @RequestMapping(value = "/query/mostvisited")
    public RecommendationWrapper findMostVisitedItems(@RequestParam(required = false, defaultValue = "01010001") @DateTimeFormat(pattern = "MMddyyyy") Date fromDate,
                                                      @RequestParam(required = false, defaultValue = "01012999") @DateTimeFormat(pattern = "MMddyyyy") Date toDate,
                                                      @RequestParam(required = false, defaultValue = "10") int limit,
                                                      @RequestParam(required = false) String mediaType) {
        if(StringUtils.isEmpty(mediaType)) {
            return new RecommendationAssembler(graphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), limit)).build();
        } else {
            return new RecommendationAssembler(graphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), limit, mediaType)).build();
        }
    }
}

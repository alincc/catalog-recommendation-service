package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.model.RecommendationWrapper;
import no.nb.microservices.recommendation.rest.assembler.RecommendationAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

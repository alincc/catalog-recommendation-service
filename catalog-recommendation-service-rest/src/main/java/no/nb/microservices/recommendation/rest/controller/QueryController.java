package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/v1/catalog/recommend")
public class QueryController {

    private final GraphQueryService graphQueryService;

    @Autowired
    public QueryController(GraphQueryService graphQueryService) {
        this.graphQueryService = graphQueryService;
    }

    @RequestMapping(value = "/query/othershavevisited")
    public ResponseEntity findOtherHaveVisited(String itemId) {
        Collection<Recommendation> recommendations = graphQueryService.findWhatOtherHaveVisited(itemId);

        return new ResponseEntity(recommendations, HttpStatus.OK);
    }
}

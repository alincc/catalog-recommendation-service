package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.graph.service.GraphInsertService;
import no.nb.microservices.recommendation.model.Item;
import no.nb.microservices.recommendation.model.ItemAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/catalog/recommend")
public class ItemController {

    private final GraphInsertService graphInsertService;

    @Autowired
    public ItemController(GraphInsertService graphInsertService) {
        this.graphInsertService = graphInsertService;
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItem(@RequestBody Item item) {
        graphInsertService.addItem(item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/items/{itemId}/actions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItemAction(@PathVariable String itemId, @RequestBody ItemAction itemAction) {
        itemAction.setItemId(itemId);
        graphInsertService.addItemAction(itemAction);
        return new ResponseEntity(HttpStatus.OK);
    }
}

package no.nb.microservices.clickstream.rest.controller;

import no.nb.microservices.clickstream.core.graph.service.IClickstreamService;
import no.nb.microservices.clickstream.model.ActionItem;
import no.nb.microservices.clickstream.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/recommend")
public class ItemController {

    private final IClickstreamService clickstreamService;

    @Autowired
    public ItemController(IClickstreamService clickstreamService) {
        this.clickstreamService = clickstreamService;
    }

    @RequestMapping(value = "/action" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addActionItem(@RequestBody ActionItem actionItem) {
        clickstreamService.addActionItem(actionItem);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItem(@RequestBody Item item) {
        clickstreamService.addItem(item);
        return new ResponseEntity(HttpStatus.OK);
    }
}

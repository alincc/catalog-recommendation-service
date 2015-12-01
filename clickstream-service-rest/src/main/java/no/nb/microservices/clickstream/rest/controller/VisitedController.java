package no.nb.microservices.clickstream.rest.controller;

import no.nb.microservices.clickstream.core.graph.service.IClickstreamService;
import no.nb.microservices.clickstream.model.NbUrl;
import no.nb.microservices.clickstream.model.TopVisitedUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visited")
public class VisitedController {
    private static String REGEX = "^[a-zA-Z0-9æøåÆØÅ]*$";
    private IClickstreamService clickstreamService;

    @Autowired
    public VisitedController(IClickstreamService clickstreamService) {
        this.clickstreamService = clickstreamService;
    }

    @RequestMapping(value = "/top/{count}/{mediatype}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity<List<TopVisitedUrl>> getTopVisited(@PathVariable String mediatype, @PathVariable int count) {

        if (!mediatype.matches(REGEX)) {
            throw new IllegalArgumentException();
        }

        List<TopVisitedUrl> visited = clickstreamService.getTopVisitedUrls(mediatype, count);
        if (visited != null && !visited.isEmpty()) {

            return new ResponseEntity<List<TopVisitedUrl>>(visited, HttpStatus.OK);
        }
        return new ResponseEntity<List<TopVisitedUrl>>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{sessionId}/{count}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity<List<NbUrl>> getLastVisited(@PathVariable String sessionId, @PathVariable int count) {

        if (!sessionId.matches(REGEX)) {
            throw new IllegalArgumentException();
        }

        List<NbUrl> urls = clickstreamService.getLastVisited(sessionId, count);
        if (urls != null && !urls.isEmpty()) {
            return new ResponseEntity<List<NbUrl>>(urls, HttpStatus.OK);
        }
        return new ResponseEntity<List<NbUrl>>(HttpStatus.NO_CONTENT);
    }
}
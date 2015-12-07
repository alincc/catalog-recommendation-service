package no.nb.microservices.clickstream.rest.controller;

import no.nb.microservices.clickstream.core.graph.service.IClickstreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/trails")
//public class TrailsController {
//
//    private static String REGEX = "^[a-zA-Z0-9\\.]*$";
//
//    private final IClickstreamService clickstreamService;
//    private final ActivityResourceAssembler assembler;
//
//    @Autowired
//    public TrailsController(IClickstreamService clickstreamService, ActivityResourceAssembler assembler) {
//        this.clickstreamService = clickstreamService;
//        this.assembler = assembler;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Trail> getTrail(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
//                                          @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Trail> addTrail(@RequestBody Trail trail) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Trail> getTrails(@PathVariable("id") String id) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    @RequestMapping(value = "/{id}/activities/{activityId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Activity> getActivity(@PathVariable("id") String id, @PathVariable("activityId") String activityId) {
//
//        if (!id.matches(REGEX) || !activityId.matches(REGEX)) {
//            throw new IllegalArgumentException();
//        }
//        Activity activity = clickstreamService.getActivity(id, activityId);
//        if (activity != null) {
//            return new ResponseEntity<Activity>(activity, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @RequestMapping(value = "/{id}/activities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PagedResources<Activity>> getActivities(@PathVariable("id") String id,
//                                                                  @RequestParam(value = "page", defaultValue = "0", required = false) int page,
//                                                                  @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
//
//        if (!id.matches(REGEX)) {
//            throw new IllegalArgumentException();
//        }
//
//        List<NbUrl> urls = clickstreamService.getActivities(id);
//
//        if (urls != null && !urls.isEmpty()) {
//            PageRequest pageRequest = new PageRequest(page, size);
//            Page<Activity> pages = clickstreamService.getActivities(id, pageRequest);
//
//            if (pages.hasContent()) {
//                PagedResources<Activity> pagedResource = assembler.toResource(new ActivitiesPage(id, pages));
//                return new ResponseEntity<PagedResources<Activity>>(pagedResource, HttpStatus.OK);
//            }
//        }
//
//        return new ResponseEntity<PagedResources<Activity>>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/{id}/activities", method = RequestMethod.POST)
//    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
//        // No input validation here, OK since cypher injections are avoided with parameterized queries
//        Activity addedActivity = clickstreamService.addActivity(activity.getSessionId(), activity.getUrl(), activity.getSesamid(), activity.getMediaType());
//        if (addedActivity != null) {
//            return new ResponseEntity<Activity>(addedActivity, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<Activity>(HttpStatus.NO_CONTENT);
//    }
//}
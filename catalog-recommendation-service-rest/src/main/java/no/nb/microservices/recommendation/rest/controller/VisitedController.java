package no.nb.microservices.recommendation.rest.controller;

//@RestController
//@RequestMapping("/visited")
//public class VisitedController {
//    private static String REGEX = "^[a-zA-Z0-9æøåÆØÅ]*$";
//    private IClickstreamService clickstreamService;
//
//    @Autowired
//    public VisitedController(IClickstreamService clickstreamService) {
//        this.clickstreamService = clickstreamService;
//    }
//
//    @RequestMapping(value = "/top/{count}/{mediatype}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public HttpEntity<List<TopVisitedUrl>> getTopVisited(@PathVariable String mediatype, @PathVariable int count) {
//
//        if (!mediatype.matches(REGEX)) {
//            throw new IllegalArgumentException();
//        }
//
//        List<TopVisitedUrl> visited = clickstreamService.getTopVisitedUrls(mediatype, count);
//        if (visited != null && !visited.isEmpty()) {
//
//            return new ResponseEntity<List<TopVisitedUrl>>(visited, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<TopVisitedUrl>>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/{sessionId}/{count}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public HttpEntity<List<NbUrl>> getLastVisited(@PathVariable String sessionId, @PathVariable int count) {
//
//        if (!sessionId.matches(REGEX)) {
//            throw new IllegalArgumentException();
//        }
//
//        List<NbUrl> urls = clickstreamService.getLastVisited(sessionId, count);
//        if (urls != null && !urls.isEmpty()) {
//            return new ResponseEntity<List<NbUrl>>(urls, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<NbUrl>>(HttpStatus.NO_CONTENT);
//    }
//}
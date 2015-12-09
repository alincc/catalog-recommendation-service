package no.nb.microservices.recommendation.rest.controller;

//import org.springframework.hateoas.PagedResources;

//@RestController
//@RequestMapping("/navigation")
//public class NavigationController {
//
//    private static String REGEX = "^[a-zA-Z0-9æøåÆØÅ]*$";
//
//    private final IClickstreamService clickstreamService;
//    private final NavigationResourceAssembler assembler;
//
//    @Autowired
//    public NavigationController(IClickstreamService clickstreamService, NavigationResourceAssembler assembler) {
//        this.clickstreamService = clickstreamService;
//        this.assembler = assembler;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PagedResources<NbUrl>> getAllNavigation(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
//                                                                  @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
//
//        PageRequest pageRequest = new PageRequest(page, size);
//        Page<NbUrl> pages = clickstreamService.getAllUrls(pageRequest);
//
//        if (pages.hasContent()) {
//            PagedResources<NbUrl> pagedResource = assembler.toResource(new NavigationPage(pages));
//            return new ResponseEntity<PagedResources<NbUrl>>(pagedResource, HttpStatus.OK);
//        }
//        return new ResponseEntity<PagedResources<NbUrl>>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getNavigation(@PathVariable("id") String id) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    @RequestMapping(value = "/{id}/trails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PagedResources<NbUrl>> getRecommendation(@PathVariable("id") String id,
//                                                                   @RequestParam(value = "page", defaultValue = "0", required = false) int page,
//                                                                   @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
//
//        if (!id.matches(REGEX)) {
//            throw new IllegalArgumentException();
//        }
//
//        PageRequest pageRequest = new PageRequest(page, size);
//        Page<NbUrl> pages = clickstreamService.getRecommendations(id, pageRequest);
//
//        if (pages.hasContent()) {
//            PagedResources<NbUrl> pagedResource = assembler.toResource(new NavigationPage(pages));
//            return new ResponseEntity<PagedResources<NbUrl>>(pagedResource, HttpStatus.OK);
//        }
//        return new ResponseEntity<PagedResources<NbUrl>>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/mostpopular", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PagedResources<NbUrl>> getMostPopular(@RequestParam(value = "mediatype", defaultValue = "all", required = false) String mediatype,
//                                                                @RequestParam(value = "page", defaultValue = "0", required = false) int page,
//                                                                @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
//
//        if (!mediatype.matches(REGEX)) {
//            throw new IllegalArgumentException();
//        }
//
//        PageRequest pageRequest = new PageRequest(page, size);
//        Page<NbUrl> pages = clickstreamService.getMostPopular(mediatype, pageRequest);
//
//        if (pages.hasContent()) {
//            PagedResources<NbUrl> pagedResource = assembler.toResource(new NavigationPage(pages));
//            return new ResponseEntity<PagedResources<NbUrl>>(pagedResource, HttpStatus.OK);
//        }
//        return new ResponseEntity<PagedResources<NbUrl>>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/topsearchterms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getTopSearchTerms(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
//                                                    @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }
//}

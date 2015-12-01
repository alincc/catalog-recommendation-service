package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.model.NavigationPage;
import no.nb.microservices.clickstream.model.NbUrl;
import no.nb.microservices.clickstream.rest.controller.NavigationController;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class NavigationResourceAssembler implements ResourceAssembler<NavigationPage, PagedResources<NbUrl>> {

    @Override
    public PagedResources<NbUrl> toResource(NavigationPage navigationPage) {
        // TODO: Page supports generic
        Page page = navigationPage.getPage();
        Collection<NbUrl> resources = new ArrayList<>();
        List<Link> links = new ArrayList<>();

        if (page == null) {
            return new PagedResources<>(resources, new PagedResources.PageMetadata(0, 0, 0, 0), links);
        }

        for (Object o : page.getContent()) {
            NbUrl nbUrl = (NbUrl) o;
//            nbUrl.add(linkTo(methodOn(NavigationController.class).getActivity(nbUrl.getSessionId(), nbUrl.getGraphId())).withSelfRel());
            resources.add(nbUrl);
        }

        if (page.hasPrevious()) {
            links.add(linkTo(methodOn(NavigationController.class).getAllNavigation(page.previousPageable().getPageNumber(), page.getSize())).withRel("prev"));
        }
        if (page.hasNext()) {
            links.add(linkTo(methodOn(NavigationController.class).getAllNavigation(page.nextPageable().getPageNumber(), page.getSize())).withRel("next"));
        }
        if (!page.isFirst()) {
            links.add(linkTo(methodOn(NavigationController.class).getAllNavigation(0, page.getSize())).withRel("first"));
        }
        if (!page.isLast()) {
            links.add(linkTo(methodOn(NavigationController.class).getAllNavigation(page.getTotalPages() - 1, page.getSize())).withRel("last"));
        }

        PageMetadata metadata = new PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new PagedResources<>(resources, metadata, links);
    }

}

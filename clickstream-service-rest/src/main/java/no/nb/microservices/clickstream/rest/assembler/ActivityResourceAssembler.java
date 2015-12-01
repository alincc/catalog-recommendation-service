package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.model.ActivitiesPage;
import no.nb.microservices.clickstream.model.Activity;
import no.nb.microservices.clickstream.rest.controller.TrailsController;
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
public class ActivityResourceAssembler implements ResourceAssembler<ActivitiesPage, PagedResources<Activity>> {

    @Override
    public PagedResources<Activity> toResource(ActivitiesPage activitiesPage) {
        // TODO: Page supports generic
        Page page = activitiesPage.getPage();
        Collection<Activity> resources = new ArrayList<>();
        List<Link> links = new ArrayList<>();

        if (page == null) {
            return new PagedResources<>(resources, new PagedResources.PageMetadata(0, 0, 0, 0), links);
        }

        for (Object o : page.getContent()) {
            Activity activity = (Activity) o;
            activity.add(linkTo(methodOn(TrailsController.class).getActivity(activity.getSessionId(), activity.getGraphId())).withSelfRel());
            resources.add(activity);
        }

        if (page.hasPrevious()) {
            links.add(linkTo(methodOn(TrailsController.class).getActivities(activitiesPage.getSessionId(), page.previousPageable().getPageNumber(), page.getSize())).withRel("prev"));
        }
        if (page.hasNext()) {
            links.add(linkTo(methodOn(TrailsController.class).getActivities(activitiesPage.getSessionId(), page.nextPageable().getPageNumber(), page.getSize())).withRel("next"));
        }
        if (!page.isFirst()) {
            links.add(linkTo(methodOn(TrailsController.class).getActivities(activitiesPage.getSessionId(), 0, page.getSize())).withRel("first"));
        }
        if (!page.isLast()) {
            links.add(linkTo(methodOn(TrailsController.class).getActivities(activitiesPage.getSessionId(), page.getTotalPages() - 1, page.getSize())).withRel("last"));
        }

        PageMetadata metadata = new PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new PagedResources<>(resources, metadata, links);
    }

}

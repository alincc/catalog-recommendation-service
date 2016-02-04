package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.model.response.RootResponse;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryControllerTest {

    @Mock
    private GraphQueryService mockGraphQueryService;

    @InjectMocks
    private QueryController queryController;

    private MockHttpServletRequest request;

    @Before
    public void init() {
        request = new MockHttpServletRequest("GET", "/catalog/v1/id1");
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);
    }

    @Test
    public void shouldGetTopVisitedItems() throws Exception {
        Collection<RecommendationQuery> recommendationQuery = new ArrayList<>();
        recommendationQuery.add(new RecommendationQuery("item1", 34));
        Date fromDate = new DateTime(2010, 1, 1, 12, 0).toDate();
        Date toDate = new DateTime(2015, 1, 1, 12, 0).toDate();
        when(mockGraphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), 10, null)).thenReturn(recommendationQuery);

        RootResponse mostVisitedItems = queryController.findMostVisitedItems(fromDate, toDate, 10, null, null, null);

        assertNotNull(mostVisitedItems);
    }

    @Test
    public void shouldGetTopVisitedBooksItems() throws Exception {
        Collection<RecommendationQuery> recommendationQuery = new ArrayList<>();
        recommendationQuery.add(new RecommendationQuery("item1", 34));
        Date fromDate = new DateTime(2010, 1, 1, 12, 0).toDate();
        Date toDate = new DateTime(2015, 1, 1, 12, 0).toDate();
        when(mockGraphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), 10, null)).thenReturn(recommendationQuery);

        RootResponse mostVisitedItems = queryController.findMostVisitedItems(fromDate, toDate, 10, "Books", null, null);

        assertNotNull(mostVisitedItems);
    }
}

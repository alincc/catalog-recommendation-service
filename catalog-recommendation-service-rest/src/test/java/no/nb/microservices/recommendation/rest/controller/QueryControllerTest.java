package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.model.RecommendationWrapper;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Test
    public void shouldGetTopVisitedItems() throws Exception {
        Collection<RecommendationQuery> recommendationQuery = new ArrayList<>();
        recommendationQuery.add(new RecommendationQuery("item1", 34));
        Date fromDate = new DateTime(2010, 1, 1, 12, 0).toDate();
        Date toDate = new DateTime(2015, 1, 1, 12, 0).toDate();
        when(mockGraphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), 10)).thenReturn(recommendationQuery);

        RecommendationWrapper mostVisitedItems = queryController.findMostVisitedItems(fromDate, toDate, 10, null);

        assertNotNull(mostVisitedItems);
    }

    @Test
    public void shouldGetTopVisitedBooksItems() throws Exception {
        Collection<RecommendationQuery> recommendationQuery = new ArrayList<>();
        recommendationQuery.add(new RecommendationQuery("item1", 34));
        Date fromDate = new DateTime(2010, 1, 1, 12, 0).toDate();
        Date toDate = new DateTime(2015, 1, 1, 12, 0).toDate();
        when(mockGraphQueryService.findMostVisitedItems(fromDate.getTime(), toDate.getTime(), 10)).thenReturn(recommendationQuery);

        RecommendationWrapper mostVisitedItems = queryController.findMostVisitedItems(fromDate, toDate, 10, "Books");

        assertNotNull(mostVisitedItems);
    }
}

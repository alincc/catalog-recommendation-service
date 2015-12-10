package no.nb.microservices.recommendation.it;

import no.nb.microservices.recommendation.config.TestNeo4jConfig;
import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.graph.repository.*;
import no.nb.microservices.recommendation.core.graph.service.GraphQueryService;
import no.nb.microservices.recommendation.core.graph.service.SimpleGraphQueryService;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestNeo4jConfig.class})
@Ignore("Doesn't save db to target - can be used local")
public class SimpleGraphQueryServiceIT {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private SearchQueryRepository searchQueryRepository;
    @Autowired
    private Neo4jOperations neo4jTemplate;

    private GraphQueryService graphQueryService;

    @Before
    public void setUp() throws Exception {
        graphQueryService = new SimpleGraphQueryService(itemRepository, sessionRepository, userRepository,
                searchRepository, locationRepository, publisherRepository, searchQueryRepository);

        createGraph();
    }

    @Test
    public void shouldFindTheMostVisitedItemsBetween2010And2016() throws Exception {
        Date fromDate = new DateTime(2010, 1, 1, 12, 0).toDate();
        Date toDate = new DateTime(2016, 1, 1, 12, 0).toDate();

        Collection<RecommendationQuery> mostVisited = graphQueryService.findMostVisited(fromDate.getTime(), toDate.getTime(), 10);

        assertThat(mostVisited, hasSize(3));
        RecommendationQuery recommendationQuery = mostVisited.iterator().next();
        assertThat(recommendationQuery.getItemId(), is("item1"));
        assertThat(recommendationQuery.getScore(), is(3.0));
    }

    @After
    public void cleanUp() {
        neo4jTemplate.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r", new HashMap<>());
    }


    private void createGraph() {
        UserNode user1 = new UserNode("user1");
        UserNode user2 = new UserNode("user2");
        UserNode user3 = new UserNode("user3");

        SessionNode session1 = new SessionNode("session1", new DateTime(2013, 10, 10, 12, 0).toDate());
        SessionNode session2 = new SessionNode("session2", new DateTime(2014, 10, 10, 12, 0).toDate());
        SessionNode session3 = new SessionNode("session3", new DateTime(2015, 10, 10, 12, 0).toDate());

        ItemNode item1 = new ItemNode("item1", "bøker", Arrays.asList());
        ItemNode item2 = new ItemNode("item2", "bøker", Arrays.asList());
        ItemNode item3 = new ItemNode("item3", "film", Arrays.asList());

        user1.addSession(session1);
        session1.addAction(item1, "VISITED");
        session1.addAction(item2, "VISITED");
        session1.addAction(item3, "VISITED");
        userRepository.save(user1);

        user2.addSession(session2);
        session2.addAction(item1, "VISITED");
        session2.addAction(item2, "VISITED");
        userRepository.save(user2);

        user3.addSession(session3);
        session3.addAction(item1, "VISITED");
        userRepository.save(user3);
    }
}

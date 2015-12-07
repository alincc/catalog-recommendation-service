package no.nb.microservices.clickstream.it;

import no.nb.microservices.clickstream.config.TestNeo4jConfig;
import no.nb.microservices.clickstream.core.graph.repository.*;
import no.nb.microservices.clickstream.core.graph.service.ClickstreamService;
import no.nb.microservices.clickstream.core.graph.service.IClickstreamService;
import no.nb.microservices.clickstream.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
@Ignore("No embedded neo4j config - needs local neo4j running")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestNeo4jConfig.class)
public class ClickstreamServiceIT {

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
    private SessionFactory sessionFactory;

    private IClickstreamService clickstreamService;

    @Before
    public void setUp()  {
        clickstreamService = new ClickstreamService(itemRepository, sessionRepository, userRepository,
                searchRepository, locationRepository, publisherRepository);
    }

    @After
    public void cleanUp() {
        final String deleteAll = "MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r";
        sessionFactory.openSession("http://localhost:7474", "neo4j", "Newton").query(deleteAll, new HashMap<>());
    }

    @Test
    @Ignore("No embedded neo4j config - needs local neo4j running")
    public void whenUserHasVisitedOneItemThenOneItemShouldHaveBeenSavedToUser() throws Exception {
        clickstreamService.addActionItem(createActionItem("item1"));

        assertThat(itemRepository.count(), is(1L));
        assertThat(sessionRepository.count(), is(1L));
        assertThat(userRepository.count(), is(1L));
        assertThat(searchRepository.count(), is(1L));
        assertThat(locationRepository.count(), is(1L));
        assertThat(publisherRepository.count(), is(1L));
    }

    @Test
    @Ignore("No embedded neo4j config - needs local neo4j running")
    public void whenUserHasVisitedTwoItemsThenTwoItemsShouldHaveBeenSavedOnSameUser() throws Exception {
        clickstreamService.addActionItem(createActionItem("item1"));
        clickstreamService.addActionItem(createActionItem("item2"));


        assertThat(itemRepository.count(), is(2L));
        assertThat(sessionRepository.count(), is(1L));
        assertThat(userRepository.count(), is(1L));
        assertThat(searchRepository.count(), is(1L));
        assertThat(locationRepository.count(), is(1L));
        assertThat(publisherRepository.count(), is(1L));
    }

    private ActionItem createActionItem(String itemId) {
        ActionItem actionItem = new ActionItem();
        actionItem.setAction("VISITED");
        actionItem.setQuery("action");

        Item item = createItem(itemId);
        actionItem.setItem(item);

        User user = createUser();
        actionItem.setUser(user);

        Session session = createSession();
        actionItem.setSession(session);

        return actionItem;
    }

    private Item createItem(String itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setPublisher("publisher");
        item.setMediaType("bøker");
        item.setTopics(Arrays.asList("action", "grøsser"));
        item.setLocation(getLocation());
        return item;
    }

    private User createUser() {
        User user = new User();
        user.setAge(18);
        user.setGender("male");
        user.setInterests(Arrays.asList("action"));
        user.setUserId("userId");
        return user;
    }

    private Session createSession() {
        Session session = new Session();
        session.setSessionId("session1");
        session.setLocation(getLocation());
        return session;
    }

    private Location getLocation() {
        Location location = new Location();
        location.setCountry("Norway");
        location.setCounty("Nordland");
        location.setMunicipality("Rana");
        return location;
    }
}

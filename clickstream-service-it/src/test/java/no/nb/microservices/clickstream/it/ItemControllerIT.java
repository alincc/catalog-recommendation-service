package no.nb.microservices.clickstream.it;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import no.nb.microservices.clickstream.Application;
import no.nb.microservices.clickstream.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RibbonClientConfiguration.class})
@WebIntegrationTest("server.port: 0")
@Ignore("No embedded neo4j config - needs local neo4j running")
public class ItemControllerIT {

    @Value("${local.server.port}")
    int port;

    @Autowired
    ILoadBalancer loadBalancer;

    @Autowired
    WebApplicationContext context;

    RestTemplate rest;

    @Before
    public void setup() throws Exception {
        rest = new TestRestTemplate();
    }

    @Autowired
    private SessionFactory sessionFactory;

    @After
    public void cleanUp() {
        final String deleteAll = "MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r";
        sessionFactory.openSession("http://localhost:7474", "neo4j", "Newton").query(deleteAll, new HashMap<>());
    }

    @Test
    @Ignore("No embedded neo4j config - needs local neo4j running")
    public void shouldAddActionItem() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + port + "/item");
        ItemAction body = createActionItem("item1");
        HttpEntity<ItemAction> requestEntity = new HttpEntity<>(body);
        ResponseEntity<Void> responseEntity = rest.postForEntity(uri, requestEntity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private ItemAction createActionItem(String itemId) {
        ItemAction itemAction = new ItemAction();
        itemAction.setAction("VISITED");
        itemAction.setQuery("action");

        Item item = createItem(itemId);
        itemAction.setItem(item);

        User user = createUser();
        itemAction.setUser(user);

        Session session = createSession();
        itemAction.setSession(session);

        return itemAction;
    }

    private Item createItem(String itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setPublisher("publisher");
        item.setMediaType("bøker");
        item.setTopics(Arrays.asList("action", "grøsser", "drama"));
        item.setLocation(getLocation());
        return item;
    }

    private User createUser() {
        User user = new User();
        user.setAge(19);
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

@Configuration
class RibbonClientConfiguration {
    @Bean
    public ILoadBalancer ribbonLoadBalancer() {
        return new BaseLoadBalancer();
    }
}
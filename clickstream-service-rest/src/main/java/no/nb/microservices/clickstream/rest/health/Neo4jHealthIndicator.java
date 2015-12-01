package no.nb.microservices.clickstream.rest.health;

import no.nb.microservices.clickstream.config.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

public class Neo4jHealthIndicator extends AbstractHealthIndicator {

    private final RestTemplate restTemplate;
    private final ApplicationSettings settings;

    @Autowired
    public Neo4jHealthIndicator(RestTemplate restTemplate, ApplicationSettings settings) {
        this.restTemplate = restTemplate;
        this.settings = settings;
    }

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        try {
            ResponseEntity<LinkedHashMap> responseEntity = restTemplate.getForEntity(settings.getNeo4jDb(), LinkedHashMap.class);
            builder.up().withDetail("neo4j_version", responseEntity.getBody().get("neo4j_version"));
        } catch (Exception error) {
            builder.down(error);
        }
    }
}

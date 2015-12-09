package no.nb.microservices.recommendation.rest.health;


import no.nb.microservices.recommendation.config.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HealthIndicatorConfiguration {

    public static class Neo4jHealthIndicatorConfiguration {
        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private ApplicationSettings settings;

        @Bean
        protected HealthIndicator neo4jHealthIndicator() {
            return new Neo4jHealthIndicator(restTemplate, settings);
        }
    }
}

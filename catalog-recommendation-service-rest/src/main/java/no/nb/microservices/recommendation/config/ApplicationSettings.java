package no.nb.microservices.recommendation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "recommendation")
public class ApplicationSettings {

    private String neo4jHost;

    public String getNeo4jHost() {
        return neo4jHost;
    }

    public void setNeo4jHost(String neo4jHost) {
        this.neo4jHost = neo4jHost;
    }
}

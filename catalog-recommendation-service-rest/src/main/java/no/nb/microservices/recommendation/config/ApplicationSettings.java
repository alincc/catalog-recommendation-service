package no.nb.microservices.recommendation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "recommendation")
public class ApplicationSettings {

    private String neo4jDb;

    public String getNeo4jDb() {
        return neo4jDb;
    }

    public void setNeo4jDb(String neo4jDb) {
        this.neo4jDb = neo4jDb;
    }
}

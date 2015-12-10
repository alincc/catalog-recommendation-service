package no.nb.microservices.recommendation.config;

import org.junit.Rule;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.Neo4jIntegrationTestRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("no.nb.microservices.recommendation.core.graph.repository")
@EnableTransactionManagement
public class TestNeo4jConfig extends Neo4jConfiguration {

    @Rule
    public Neo4jIntegrationTestRule databaseServerRule = new Neo4jIntegrationTestRule();

    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer(databaseServerRule.url());
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("no.nb.microservices.recommendation.core.graph.model");
    }

    @Bean
    public Neo4jOperations neo4jTemplate() throws Exception {
        return new Neo4jTemplate(getSession());
    }
}

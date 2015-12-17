package no.nb.microservices.recommendation.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("no.nb.microservices.recommendation.core.graph.repository")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("no.nb.microservices.recommendation.core.graph.model");
    }

    @Override
    @Bean
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://localhost:7474", "neo4j", "Newton");
    }

    @Override
    @Bean
    //@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }
}
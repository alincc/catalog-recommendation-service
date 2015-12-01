package no.nb.microservices.clickstream.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "VISITED")
public class Visited extends AbstractEntity {

    @StartNode
    private NbUser user;

    @EndNode
    private NbUrlNode url;

    private Long timestamp;

    /**
     * Neo4j requires empty constructor
     */
    protected Visited() {
    }

    /**
     * @param user      user that visited the url
     * @param url       the url that the user visited
     * @param timestamp the timestamp when the user visited the url
     */
    public Visited(NbUser user, NbUrlNode url, Long timestamp) {
        this.user = user;
        this.url = url;
        this.timestamp = timestamp;
    }

    public NbUser getUser() {
        return user;
    }

    public NbUrlNode getUrl() {
        return url;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}

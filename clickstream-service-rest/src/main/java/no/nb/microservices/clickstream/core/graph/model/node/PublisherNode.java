package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Publisher")
public class PublisherNode {
    private Long id;
    private String name;

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private LocationNode location;

    public PublisherNode() {
    }

    public PublisherNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(LocationNode location) {
        this.location = location;
    }
}

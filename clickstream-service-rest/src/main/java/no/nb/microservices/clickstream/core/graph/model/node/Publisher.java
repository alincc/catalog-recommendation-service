package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Publisher {
    private Long id;
    private String name;

    @Relationship(type = "HAS_LOCATION", direction = Relationship.OUTGOING)
    private Location location;

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

package no.nb.microservices.clickstream.model;

import org.springframework.data.neo4j.annotation.GraphId;

public class AbstractEntity {

    @GraphId
    private Long id;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (id == null) {
            return false;
        }
        if (!(other instanceof AbstractEntity)) {
            return false;
        }

        return id.equals(((AbstractEntity) other).getId());
    }

    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }
}

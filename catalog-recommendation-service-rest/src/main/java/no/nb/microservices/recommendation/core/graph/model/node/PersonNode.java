package no.nb.microservices.recommendation.core.graph.model.node;

import no.nb.microservices.recommendation.core.graph.model.edge.HasRole;
import no.nb.microservices.recommendation.core.graph.model.edge.Published;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Person")
public class PersonNode {
    private Long id;

    @Index
    private String name;

    @Relationship(type = "HAS_ROLE", direction = Relationship.OUTGOING)
    private HasRole hasRole;

    public PersonNode() {
    }

    public PersonNode(String name) {
        this.name = name;
    }

    public void setHasRole(HasRole hasRole) {
        this.hasRole = hasRole;
    }
}

package no.nb.microservices.recommendation.core.graph.model.edge;


import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.PersonNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "PUBLISHED")
public class HasRole {

    @GraphId
    private Long id;

    private String role;

    @StartNode
    private PersonNode personNode;

    @EndNode
    private ItemNode itemNode;

    public HasRole() {

    }

    public HasRole(PersonNode personNode, ItemNode itemNode, String role) {
        this.personNode = personNode;
        this.itemNode = itemNode;
        this.role = role;
    }
}

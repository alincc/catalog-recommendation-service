package no.nb.microservices.recommendation.core.graph.model.node;


import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Municipality")
public class MunicipalityNode {

    private Long id;

    @Index
    private String municipality;

    @Relationship(type = "LOCATED_IN", direction = Relationship.OUTGOING)
    private CountyNode countyNode;

    public MunicipalityNode() {
    }

    public MunicipalityNode(String municipality) {
        this.municipality = municipality;
    }

    public MunicipalityNode(String municipality, CountyNode countyNode) {
        this.municipality = municipality;
        this.countyNode = countyNode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public CountyNode getCountyNode() {
        return countyNode;
    }

    public void setCountyNode(CountyNode countyNode) {
        this.countyNode = countyNode;
    }
}

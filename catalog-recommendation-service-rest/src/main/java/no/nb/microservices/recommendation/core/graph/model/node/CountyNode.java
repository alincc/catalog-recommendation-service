package no.nb.microservices.recommendation.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "County")
public class CountyNode {

    private Long id;
    private String county;

    @Relationship(type = "IN_COUNTRY", direction = Relationship.OUTGOING)
    private CountryNode countryNode;

    public CountyNode() {
    }

    public CountyNode(String county) {
        this.county = county;
    }

    public CountyNode(String county, CountryNode countryNode) {
        this.county = county;
        this.countryNode = countryNode;
    }

    public String getCounty() {
        return county;
    }

    public CountryNode getCountryNode() {
        return countryNode;
    }

    public void setCountryNode(CountryNode countryNode) {
        this.countryNode = countryNode;
    }
}

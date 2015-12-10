package no.nb.microservices.recommendation.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Country")
public class CountryNode {

    private Long id;
    private String country;

    public CountryNode() {
    }

    public CountryNode(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}

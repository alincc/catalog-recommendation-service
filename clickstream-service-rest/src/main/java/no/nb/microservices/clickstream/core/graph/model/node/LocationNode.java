package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Location")
public class LocationNode {

    private Long id;
    private String municipality;
    private String county;
    private String country;

    public LocationNode() {
    }

    public LocationNode(String municipality, String county, String country) {
        this.municipality = municipality;
        this.county = county;
        this.country = country;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }
}

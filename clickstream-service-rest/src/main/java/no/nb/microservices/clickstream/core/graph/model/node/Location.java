package no.nb.microservices.clickstream.core.graph.model.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class Location {

    private Long id;
    private String municipality;
    private String county;

    public Location() {
    }

    public Location(String municipality, String county) {
        this.municipality = municipality;
        this.county = county;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getCounty() {
        return county;
    }
}

package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.node.LocationNode;

public class LocationNodeBuilder {

    private LocationNode nodeLocation;

    public LocationNodeBuilder(no.nb.microservices.clickstream.model.Location location) {
        this.nodeLocation = new LocationNode(location.getMunicipality(), location.getCounty(), location.getCountry());

    }

    public LocationNode build() {
        return nodeLocation;
    }
}


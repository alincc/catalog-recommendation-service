package no.nb.microservices.clickstream.core.graph.build;

import no.nb.microservices.clickstream.core.graph.model.node.LocationNode;
import no.nb.microservices.clickstream.core.graph.model.node.SessionNode;
import no.nb.microservices.clickstream.core.graph.repository.LocationRepository;

public class LocationNodeBuilder {

    private SessionNode sessionNode;
    private LocationNode locationNode;
    private LocationRepository locationRepository;

    public LocationNodeBuilder(no.nb.microservices.clickstream.model.Location location, LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        this.locationNode = new LocationNode(location.getMunicipality(), location.getCounty(), location.getCountry());
    }

    public LocationNodeBuilder(no.nb.microservices.clickstream.model.Location location, SessionNode sessionNode, LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        this.sessionNode = sessionNode;
        this.locationNode = new LocationNode(location.getMunicipality(), location.getCounty(), location.getCountry());
    }

    public LocationNode build() {
        if (sessionNode != null) {
            if (locationNode != null && sessionNode.getLocation() == null) {
                LocationNode location = locationRepository.findByMunicipalityAndCounty(locationNode.getMunicipality(), locationNode.getCounty());
                return (location == null) ? locationNode : location;
            }
        }
        else {
            LocationNode newLocationNode = locationRepository.findByMunicipalityAndCounty(locationNode.getMunicipality(), locationNode.getCounty());
            return (newLocationNode == null) ? locationRepository.save(locationNode) : newLocationNode;
        }

        return locationNode;
    }
}


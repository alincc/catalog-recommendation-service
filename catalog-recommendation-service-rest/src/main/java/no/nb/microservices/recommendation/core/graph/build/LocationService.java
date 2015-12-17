package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.MunicipalityNode;
import no.nb.microservices.recommendation.model.query.Location;

public interface LocationService {
    MunicipalityNode getLocation(Location location);
}

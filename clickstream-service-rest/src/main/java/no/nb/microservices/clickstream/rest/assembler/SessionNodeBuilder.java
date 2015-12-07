package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.node.Location;
import no.nb.microservices.clickstream.core.graph.model.node.Session;

public class SessionNodeBuilder {

    private Session nodeSession;

    public SessionNodeBuilder(no.nb.microservices.clickstream.model.Session session) {
        this.nodeSession = new Session(
                session.getSessionId(),
                new Location(
                        session.getLocation().getMunicipality(),
                        session.getLocation().getCounty(),
                        session.getLocation().getCountry())
        );

    }

    public Session build() {
        return nodeSession;
    }
}


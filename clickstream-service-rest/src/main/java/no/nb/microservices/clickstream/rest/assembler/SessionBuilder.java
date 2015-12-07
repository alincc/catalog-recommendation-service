package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.node.Location;
import no.nb.microservices.clickstream.model.Item;
import no.nb.microservices.clickstream.model.Session;

public class SessionBuilder {

    private Session session;

    public SessionBuilder(Session item) {
        this.session = item;
    }

    public no.nb.microservices.clickstream.core.graph.model.node.Session build() {
        no.nb.microservices.clickstream.core.graph.model.node.Session nodeSession = new no.nb.microservices.clickstream.core.graph.model.node.Session(
                session.getSessionId(),
                new Location(session.getLocation().getMunicipality(), session.getLocation().getCounty(), session.getLocation().getCounty())
        );

        return nodeSession;
    }
}


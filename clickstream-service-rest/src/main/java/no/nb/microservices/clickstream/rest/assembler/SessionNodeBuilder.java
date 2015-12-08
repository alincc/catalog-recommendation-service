package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.node.SessionNode;

public class SessionNodeBuilder {

    private SessionNode nodeSession;

    public SessionNodeBuilder(no.nb.microservices.clickstream.model.Session session) {
        this.nodeSession = new SessionNode(session.getSessionId());

    }

    public SessionNode build() {
        return nodeSession;
    }
}


package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.model.Session;

public class SessionNodeBuilder {

    private SessionNode nodeSession;

    public SessionNodeBuilder(Session session) {
        this.nodeSession = new SessionNode(session.getSessionId());

    }

    public SessionNode build() {
        return nodeSession;
    }
}


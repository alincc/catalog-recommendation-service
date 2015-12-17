package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.model.query.Session;

public interface SessionService {

    SessionNode getSession(UserNode userNode, Session session);

    SessionNode saveSession(SessionNode sessionNode);
}

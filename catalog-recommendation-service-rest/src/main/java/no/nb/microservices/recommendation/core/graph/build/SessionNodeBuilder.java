package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.core.graph.repository.SessionRepository;
import no.nb.microservices.recommendation.model.query.Session;

import java.util.Optional;

public class SessionNodeBuilder {

    private Session session;
    private UserNode userNode;
    private SessionRepository sessionRepository;

    public SessionNodeBuilder(UserNode userNode, Session session, SessionRepository sessionRepository) {
        this.session = session;
        this.userNode = userNode;
        this.sessionRepository = sessionRepository;

    }

    public SessionNode build() {
        if (userNode != null) {
            Optional<SessionNode> sessionOptional = userNode.getSessionNodes().stream().filter(q -> q.getSessionId().equalsIgnoreCase(session.getSessionId())).findFirst();
            return (sessionOptional.isPresent() ? sessionOptional.get() : sessionRepository.save(new SessionNode(session.getSessionId(), session.getDate() )));
        } else {
            SessionNode sessionNode = sessionRepository.findBySessionId(session.getSessionId());
            return (sessionNode == null) ? new SessionNode(session.getSessionId(), session.getDate()) : sessionNode;
        }
    }
}


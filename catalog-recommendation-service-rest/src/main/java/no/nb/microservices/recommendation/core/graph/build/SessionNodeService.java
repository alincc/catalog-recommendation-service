package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.core.graph.repository.SessionRepository;
import no.nb.microservices.recommendation.model.query.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionNodeService implements SessionService {


    private final SessionRepository sessionRepository;
    private final LocationService locationService;

    @Autowired
    public SessionNodeService(SessionRepository sessionRepository, LocationService locationService) {
        this.sessionRepository = sessionRepository;
        this.locationService = locationService;
    }

    @Override
    public SessionNode getSession(UserNode userNode, Session session) {
        if(userNode != null) {
            Optional<SessionNode> sessionNodeOptional = userNode.getSessionNodes()
                    .stream()
                    .filter(s -> s.getSessionId().equalsIgnoreCase(session.getSessionId()))
                    .findFirst();

            if(sessionNodeOptional.isPresent()) {
                return sessionNodeOptional.get();
            }
        }

        SessionNode sessionNode = new SessionNode(session.getSessionId(), session.getDate());
        sessionNode.setLocation(locationService.getLocation(session.getLocation()));
        return sessionRepository.save(sessionNode);
    }

    @Override
    public SessionNode saveSession(SessionNode sessionNode) {
        return sessionRepository.save(sessionNode);
    }
}

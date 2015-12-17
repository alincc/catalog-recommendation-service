package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.model.query.User;

public interface UserService {
    UserNode getUser(User user);

    void save(UserNode userNode);
}

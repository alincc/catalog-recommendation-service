package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.core.graph.repository.UserRepository;
import no.nb.microservices.recommendation.model.query.User;

public class UserNodeBuilder {

    private User user;
    private UserRepository userRepository;

    public UserNodeBuilder(User user, UserRepository userRepository) {
        this.user = user;
        this.userRepository = userRepository;
    }

    public UserNode build() {
        if (user == null) {
            return null;
        }

        UserNode userNode = userRepository.findByUserId(user.getUserId());
        if (userNode == null) {
            userNode = userRepository.save(new UserNode(
                    user.getUserId(),
                    user.getAge(),
                    user.getGender(),
                    user.getInterests()));
        }
        return userNode;
    }
}


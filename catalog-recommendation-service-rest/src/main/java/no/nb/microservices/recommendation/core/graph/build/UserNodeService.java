package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.core.graph.repository.UserRepository;
import no.nb.microservices.recommendation.model.query.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNodeService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserNodeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserNode getUser(User user) {
        if(isAnonymousUser(user)) {
            return null;
        } else {
            UserNode userNode = userRepository.findByUserId(user.getUserId());
            if(userNode == null) {
                userNode = userRepository.save(new UserNode(user.getUserId(), user.getAge(), user.getGender(), user.getInterests()));
            }
            return userNode;
        }
    }

    private boolean isAnonymousUser(User user) {
        return user == null;
    }

    @Override
    public void save(UserNode userNode) {
        userRepository.save(userNode);
    }
}

package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.model.User;

public class UserNodeBuilder {

    private UserNode nodeUser;

    public UserNodeBuilder(User user) {
        this.nodeUser = new UserNode(
                user.getUserId(),
                user.getAge(),
                user.getGender(),
                user.getInterests()
        );
    }

    public UserNode build() {
        return nodeUser;
    }
}


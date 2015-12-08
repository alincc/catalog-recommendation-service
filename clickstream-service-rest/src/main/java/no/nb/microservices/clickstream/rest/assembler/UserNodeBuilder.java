package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.node.UserNode;

public class UserNodeBuilder {

    private UserNode nodeUser;

    public UserNodeBuilder(no.nb.microservices.clickstream.model.User user) {
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


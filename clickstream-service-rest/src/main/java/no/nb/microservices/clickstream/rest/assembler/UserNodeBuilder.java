package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.node.User;

public class UserNodeBuilder {

    private User nodeUser;

    public UserNodeBuilder(no.nb.microservices.clickstream.model.User user) {
        this.nodeUser = new User(
                user.getUserId(),
                user.getAge(),
                user.getGender(),
                user.getInterests()
        );
    }

    public User build() {
        return nodeUser;
    }
}


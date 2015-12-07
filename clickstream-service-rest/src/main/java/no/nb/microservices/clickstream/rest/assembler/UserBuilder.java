package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.model.User;

public class UserBuilder {

    private User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public no.nb.microservices.clickstream.core.graph.model.node.User build() {
        no.nb.microservices.clickstream.core.graph.model.node.User nodeUser = new no.nb.microservices.clickstream.core.graph.model.node.User(
                user.getUserId(),
                user.getAge(),
                user.getGender(),
                user.getInterests()
        );

        return nodeUser;
    }
}


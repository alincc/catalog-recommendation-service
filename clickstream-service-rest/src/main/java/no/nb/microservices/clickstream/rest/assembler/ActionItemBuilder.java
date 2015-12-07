package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.action.ActionItem;
import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Location;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
import no.nb.microservices.clickstream.core.graph.model.node.User;

import java.util.Collection;

public class ActionItemBuilder {

    public ActionItem actionItemNode;

    public ActionItemBuilder() {
        actionItemNode = new ActionItem();
    }

    public ActionItemBuilder(no.nb.microservices.clickstream.model.ActionItem actionItemModel) {
        Item itemNode = new ItemNodeBuilder(actionItemModel.getItem()).build();
        Session session = new SessionNodeBuilder(actionItemModel.getSession()).build();
        User user = new UserNodeBuilder(actionItemModel.getUser()).build();

        actionItemNode = new ActionItem();
        actionItemNode.setAction(actionItemModel.getAction());
        actionItemNode.setQuery(actionItemModel.getQuery());
        actionItemNode.setItem(itemNode);
        actionItemNode.setSession(session);
        actionItemNode.setUser(user);
    }

    public ActionItemBuilder withAction(String action) {
        this.actionItemNode.setAction(action);
        return this;
    }

    public ActionItemBuilder withQuery(String query) {
        this.actionItemNode.setQuery(query);
        return this;
    }

    public ActionItemBuilder withUser(String userId) {
        this.actionItemNode.setUser(new User(userId));
        return this;
    }

    public ActionItemBuilder withUser(String userId, int age, String gender, Collection<String> intrests) {
        this.actionItemNode.setUser(new User(userId, age, gender, intrests));
        return this;
    }

    public ActionItemBuilder withSession(String sessionId) {
        this.actionItemNode.setSession(new Session(sessionId));
        return this;
    }

    public ActionItemBuilder withSession(String sessionId, String municipality, String county, String country) {
        this.actionItemNode.setSession(new Session(sessionId, new Location(municipality, county, country)));
        return this;
    }

    public ActionItemBuilder withItem(String itemId, String mediaType, Collection<String> topics) {
        this.actionItemNode.setItem(new Item(itemId, mediaType, topics));
        return this;
    }

    public ActionItem build() {
        return actionItemNode;
    }
}

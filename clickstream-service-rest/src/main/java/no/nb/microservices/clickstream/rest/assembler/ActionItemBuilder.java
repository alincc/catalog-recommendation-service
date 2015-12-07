package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.action.ActionItem;
import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Session;
import no.nb.microservices.clickstream.core.graph.model.node.User;

public class ActionItemBuilder {

    public ActionItem actionItemNode;
    public no.nb.microservices.clickstream.model.ActionItem actionItemModel;

    public ActionItemBuilder(no.nb.microservices.clickstream.model.ActionItem actionItemModel) {
        this.actionItemModel = actionItemModel;
    }

    public ActionItem build() {
        Item itemNode = new ItemNodeBuilder(actionItemModel.getItem()).build();
        Session session = new SessionNodeBuilder(actionItemModel.getSession()).build();
        User user = new UserNodeBuilder(actionItemModel.getUser()).build();

        actionItemNode = new ActionItem();
        actionItemNode.setAction(actionItemModel.getAction());
        actionItemNode.setQuery(actionItemModel.getQuery());
        actionItemNode.setItem(itemNode);
        actionItemNode.setSession(session);
        actionItemNode.setUser(user);

        return actionItemNode;
    }
}

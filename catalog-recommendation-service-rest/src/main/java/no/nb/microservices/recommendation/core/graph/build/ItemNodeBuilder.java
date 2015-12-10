package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.model.Item;

import java.util.Collection;


public class ItemNodeBuilder {

    private ItemNode itemNode;

    public ItemNodeBuilder() {
        itemNode = new ItemNode();
    }

    public ItemNodeBuilder(Item modelItem) {
        this.itemNode = new ItemNode(modelItem.getItemId(), modelItem.getMediaType(), modelItem.getTopics());
    }

    public ItemNodeBuilder withMediaType(String mediaType) {
        this.itemNode.setMediaType(mediaType);
        return this;
    }

    public ItemNodeBuilder withItemId(String itemId) {
        this.itemNode.setItemId(itemId);
        return this;
    }

    public ItemNodeBuilder withTopics(Collection<String> topics) {
        this.itemNode.setTopics(topics);
        return this;
    }

    public ItemNode build() {
        return itemNode;
    }
}


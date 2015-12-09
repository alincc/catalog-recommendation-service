package no.nb.microservices.clickstream.core.graph.build;

import no.nb.microservices.clickstream.core.graph.model.node.ItemNode;
import no.nb.microservices.clickstream.core.graph.model.node.LocationNode;

import java.util.Collection;


public class ItemNodeBuilder {

    private ItemNode itemNode;

    public ItemNodeBuilder() {
        itemNode = new ItemNode();
    }

    public ItemNodeBuilder(no.nb.microservices.clickstream.model.Item modelItem) {
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

    public ItemNodeBuilder withLocation(String country, String county, String municipality) {
        this.itemNode.setLocation(new LocationNode(municipality, county, country));
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


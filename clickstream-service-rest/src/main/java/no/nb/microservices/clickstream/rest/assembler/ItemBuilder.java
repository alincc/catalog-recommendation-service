package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.model.Item;

public class ItemBuilder {

    private Item item;

    public ItemBuilder(Item item) {
        this.item = item;
    }

    public no.nb.microservices.clickstream.core.graph.model.node.Item build() {
        no.nb.microservices.clickstream.core.graph.model.node.Item nodeItem = new no.nb.microservices.clickstream.core.graph.model.node.Item(
                item.getItemId(),
                item.getMediaType(),
                item.getTopics()
        );

        return nodeItem;
    }
}


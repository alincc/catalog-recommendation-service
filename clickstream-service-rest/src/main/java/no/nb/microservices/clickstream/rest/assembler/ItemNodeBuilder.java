package no.nb.microservices.clickstream.rest.assembler;

import no.nb.microservices.clickstream.core.graph.model.node.Item;
import no.nb.microservices.clickstream.core.graph.model.node.Location;
import no.nb.microservices.clickstream.core.graph.model.node.Publisher;

import java.util.Collection;


public class ItemNodeBuilder {

    private Item itemNode;

    public ItemNodeBuilder() {
        itemNode = new Item();
    }

    public ItemNodeBuilder(no.nb.microservices.clickstream.model.Item modelItem) {
        this.itemNode = new Item(modelItem.getItemId(), modelItem.getMediaType(), modelItem.getTopics());
        this.itemNode.setPublisher(new Publisher(modelItem.getPublisher()));
        this.itemNode.setLocation(new Location(
                modelItem.getLocation().getMunicipality(),
                modelItem.getLocation().getCounty(),
                modelItem.getLocation().getCountry()));
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
        this.itemNode.setLocation(new Location(municipality, county, country));
        return this;
    }

    public ItemNodeBuilder withPublisher(Publisher publisher) {
        this.itemNode.setPublisher(publisher);
        return this;
    }

    public ItemNodeBuilder withTopics(Collection<String> topics) {
        this.itemNode.setTopics(topics);
        return this;
    }

    public Item build() {
        return itemNode;
    }
}


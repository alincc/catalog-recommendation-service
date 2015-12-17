package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.model.query.Item;

public interface ItemService {
    void saveItem(Item item);

    ItemNode getItem(String itemId);
}

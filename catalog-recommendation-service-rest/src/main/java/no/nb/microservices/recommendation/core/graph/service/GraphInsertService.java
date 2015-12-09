package no.nb.microservices.recommendation.core.graph.service;


import no.nb.microservices.recommendation.model.Item;
import no.nb.microservices.recommendation.model.ItemAction;

public interface GraphInsertService {
    void addItemAction(ItemAction itemAction);

    void addItem(Item item);
}

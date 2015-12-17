package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.repository.ItemRepository;
import no.nb.microservices.recommendation.model.query.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemNodeService implements ItemService {


    private final ItemRepository itemRepository;
    private final LocationService locationService;
    private final PublisherService publisherService;

    @Autowired
    public ItemNodeService(ItemRepository itemRepository, LocationService locationService, PublisherService publisherService) {
        this.itemRepository = itemRepository;
        this.locationService = locationService;
        this.publisherService = publisherService;
    }

    @Override
    public void saveItem(Item item) {
        ItemNode itemNode = itemRepository.findByItemId(item.getItemId());
        if(itemNode == null) {
            itemNode = new ItemNode(item.getItemId(), item.getMediaType(), item.getTopics());
            itemNode.setLocation(locationService.getLocation(item.getLocation()));
            itemNode.setPublisher(publisherService.getPublisher(item.getPublisher()));
            itemRepository.save(itemNode);
        }
    }

    @Override
    public ItemNode getItem(String itemId) {
        return itemRepository.findByItemId(itemId);
    }
}

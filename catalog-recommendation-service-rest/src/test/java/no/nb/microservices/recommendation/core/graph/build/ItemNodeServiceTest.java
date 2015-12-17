package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.repository.ItemRepository;
import no.nb.microservices.recommendation.model.query.Item;
import no.nb.microservices.recommendation.model.query.Location;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemNodeServiceTest {

    @Mock
    private PublisherService publisherService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private ItemNodeService itemService;

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(locationService);
        verifyNoMoreInteractions(publisherService);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void whenNewItemThenSaveIt() throws Exception {
        Item item = createDefaultItem();

        when(itemRepository.findByItemId(item.getItemId())).thenReturn(null);
        when(locationService.getLocation(item.getLocation())).thenReturn(null);
        when(publisherService.getPublisher(item.getPublisher())).thenReturn(null);
        when(itemRepository.save(any(ItemNode.class))).thenReturn(new ItemNode());

        itemService.saveItem(item);

        verify(itemRepository).findByItemId(item.getItemId());
        verify(locationService).getLocation(item.getLocation());
        verify(publisherService).getPublisher(item.getPublisher());
        verify(itemRepository).save(any(ItemNode.class));
    }

    @Test
    public void whenItemAlreadyExistsThenDoNothing() throws Exception {
        Item item = createDefaultItem();

        when(itemRepository.findByItemId(item.getItemId())).thenReturn(new ItemNode());
        itemService.saveItem(item);
        verify(itemRepository).findByItemId(item.getItemId());
    }

    private Item createDefaultItem() {
        Item item = new Item("item1", "bøker", Arrays.asList("bil", "motor"));
        item.setPublisher("publisher");
        item.setLocation(new Location("country", "county", "municipality"));
        return item;
    }
}

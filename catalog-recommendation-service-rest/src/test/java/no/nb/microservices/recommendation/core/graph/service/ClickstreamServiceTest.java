package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.repository.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClickstreamServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private ClickstreamService clickstreamService;

    @Test
    @Ignore
    public void addActionItemTest() {
//        Item item = new ItemNodeBuilder()
//                .withItemId("id1")
//                .withMediaType("bøker")
//                .build();
//
//        when(itemRepository.merge(any(Item.class))).thenReturn(item);
//
//        ActionItem actionItem = new ActionItem();
//        no.nb.microservices.clickstream.model.Item modelItem = new no.nb.microservices.clickstream.model.Item();
//        modelItem.setItemId("id1");
//        modelItem.setMediaType("bøker");
//        actionItem.setItem(modelItem);
//        clickstreamService.addItemAction(actionItem);
    }

}

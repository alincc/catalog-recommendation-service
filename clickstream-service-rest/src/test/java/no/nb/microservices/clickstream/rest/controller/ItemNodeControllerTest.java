package no.nb.microservices.clickstream.rest.controller;

import no.nb.microservices.clickstream.core.graph.service.IClickstreamService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItemNodeControllerTest {

    @Mock
    private IClickstreamService mockedItemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    @Ignore
    public void shouldAddActionItem() throws Exception {
//        ActionItem actionItem = new ActionItem();
//
//        itemController.addItemAction(actionItem);
    }
}

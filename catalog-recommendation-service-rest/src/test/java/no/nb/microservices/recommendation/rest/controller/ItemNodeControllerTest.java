package no.nb.microservices.recommendation.rest.controller;

import no.nb.microservices.recommendation.core.graph.service.GraphInsertService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItemNodeControllerTest {

    @Mock
    private GraphInsertService mockedItemService;

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

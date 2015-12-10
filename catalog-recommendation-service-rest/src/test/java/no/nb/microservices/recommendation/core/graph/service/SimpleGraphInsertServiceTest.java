package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.LocationNode;
import no.nb.microservices.recommendation.core.graph.model.node.PublisherNode;
import no.nb.microservices.recommendation.core.graph.repository.*;
import no.nb.microservices.recommendation.model.Item;
import no.nb.microservices.recommendation.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleGraphInsertServiceTest {

    @Mock
    private ItemRepository mockItemRepository;

    @Mock
    private SessionRepository mockSessionRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private SearchRepository mockSearchRepository;

    @Mock
    private LocationRepository mockLocationRepository;

    @Mock
    private PublisherRepository mockPublisherRepository;

    @InjectMocks
    private SimpleGraphInsertService simpleGraphInsertService;

    @Test
    public void ifItemAndLocationAndPublisherDontExistsThenAddThemToGraph() {
        Item item = createItem();
        mockCallsToSaveItemAndPublisherAndLocation(item);
        simpleGraphInsertService.addItem(item);
        verifyThatItemAndLocationAndPublisherGotSaved(item);
    }

    private void mockCallsToSaveItemAndPublisherAndLocation(Item item) {
        Location location = item.getLocation();

        PublisherNode publisherNode = new PublisherNode(item.getPublisher());
        LocationNode locationNode = new LocationNode(location.getMunicipality(), location.getCounty(), location.getCountry());
        ItemNode itemNode = new ItemNode(item.getItemId());
        itemNode.setLocation(locationNode);
        itemNode.setPublisher(publisherNode);

        when(mockItemRepository.findByItemId(item.getItemId())).thenReturn(null);
        when(mockPublisherRepository.findByName(item.getPublisher())).thenReturn(null);
        when(mockPublisherRepository.save(any(PublisherNode.class))).thenReturn(publisherNode);
        when(mockLocationRepository.findByMunicipalityAndCounty(location.getMunicipality(), location.getCounty())).thenReturn(null);
        when(mockLocationRepository.save(any(LocationNode.class))).thenReturn(locationNode);
        when(mockItemRepository.save(any(ItemNode.class))).thenReturn(itemNode);
    }

    private void verifyThatItemAndLocationAndPublisherGotSaved(Item item) {
        verify(mockItemRepository).findByItemId(item.getItemId());
        verify(mockPublisherRepository).findByName(item.getPublisher());
        verify(mockPublisherRepository).save(any(PublisherNode.class));
        verify(mockLocationRepository).findByMunicipalityAndCounty(item.getLocation().getMunicipality(), item.getLocation().getCounty());
        verify(mockLocationRepository).save(any(LocationNode.class));
        verify(mockItemRepository).save(any(ItemNode.class));
        verifyNoMoreInteractions(mockItemRepository);
        verifyNoMoreInteractions(mockPublisherRepository);
        verifyNoMoreInteractions(mockLocationRepository);
    }

    @Test
    public void ifPublisherAndLocationExistsButNotItemThenSaveOnlyItemNodeAndConnectThem() throws Exception {
        Item item = createItem();

        mockCallsToSaveOnlyItem(item);
        simpleGraphInsertService.addItem(item);
        verifyThatOnlyItemGotSaved(item);
    }

    private void mockCallsToSaveOnlyItem(Item item) {
        Location location = item.getLocation();

        PublisherNode publisherNode = new PublisherNode(item.getPublisher());
        LocationNode locationNode = new LocationNode(location.getMunicipality(), location.getCounty(), location.getCountry());
        ItemNode itemNode = new ItemNode(item.getItemId());
        itemNode.setLocation(locationNode);
        itemNode.setPublisher(publisherNode);

        when(mockItemRepository.findByItemId(item.getItemId())).thenReturn(null);
        when(mockPublisherRepository.findByName(item.getPublisher())).thenReturn(publisherNode);
        when(mockLocationRepository.findByMunicipalityAndCounty(location.getMunicipality(), location.getCounty())).thenReturn(locationNode);
        when(mockItemRepository.save(any(ItemNode.class))).thenReturn(itemNode);
    }

    private void verifyThatOnlyItemGotSaved(Item item) {
        verify(mockItemRepository).findByItemId(item.getItemId());
        verify(mockPublisherRepository).findByName(item.getPublisher());
        verify(mockLocationRepository).findByMunicipalityAndCounty(item.getLocation().getMunicipality(), item.getLocation().getCounty());
        verify(mockItemRepository).save(any(ItemNode.class));
        verifyNoMoreInteractions(mockItemRepository);
        verifyNoMoreInteractions(mockPublisherRepository);
        verifyNoMoreInteractions(mockLocationRepository);
    }

    private Item createItem() {
        String publisher = "publisher";
        String municipality = "municipality";
        String county = "county";
        String country = "country";
        Item item = new Item();
        item.setPublisher(publisher);
        Location location = new Location();
        location.setMunicipality(municipality);
        location.setCounty(county);
        location.setCountry(country);
        item.setLocation(location);
        return item;
    }
}

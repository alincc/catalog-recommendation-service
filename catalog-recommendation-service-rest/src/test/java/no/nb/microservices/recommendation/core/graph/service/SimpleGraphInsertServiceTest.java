package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.model.node.*;
import no.nb.microservices.recommendation.core.graph.repository.*;
import no.nb.microservices.recommendation.model.query.Item;
import no.nb.microservices.recommendation.model.query.Location;
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
    private PublisherRepository mockPublisherRepository;

    @Mock
    private CountryRepository mockCountryRepository;

    @Mock
    private CountyRepository mockCountyRepository;

    @Mock
    private MunicipalityRepository mockMunicipalityRepository;

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
        ItemNode itemNode = new ItemNode(item.getItemId());
        itemNode.setLocation(new MunicipalityNode(location.getMunicipality()));
        itemNode.setPublisher(publisherNode);

        when(mockItemRepository.findByItemId(item.getItemId())).thenReturn(null);
        when(mockPublisherRepository.findByName(item.getPublisher())).thenReturn(null);
        when(mockPublisherRepository.save(any(PublisherNode.class))).thenReturn(publisherNode);
        when(mockMunicipalityRepository.findByMunicipality(location.getMunicipality())).thenReturn(null);
        when(mockCountyRepository.findByCounty(location.getCounty())).thenReturn(null);
        when(mockCountryRepository.findByCountry(location.getCountry())).thenReturn(null);
        when(mockMunicipalityRepository.save(any(MunicipalityNode.class))).thenReturn(new MunicipalityNode("municipality"));
        when(mockCountyRepository.save(any(CountyNode.class))).thenReturn(new CountyNode("county"));
        when(mockCountryRepository.save(any(CountryNode.class))).thenReturn(new CountryNode("country"));
        when(mockItemRepository.save(any(ItemNode.class))).thenReturn(itemNode);
    }

    private void verifyThatItemAndLocationAndPublisherGotSaved(Item item) {
        verify(mockItemRepository).findByItemId(item.getItemId());
        verify(mockPublisherRepository).findByName(item.getPublisher());
        verify(mockPublisherRepository).save(any(PublisherNode.class));
        verify(mockMunicipalityRepository).findByMunicipality(item.getLocation().getMunicipality());
        verify(mockCountyRepository).findByCounty(item.getLocation().getCounty());
        verify(mockCountryRepository).findByCountry(item.getLocation().getCountry());
        verify(mockMunicipalityRepository).save(any(MunicipalityNode.class));
        verify(mockItemRepository).save(any(ItemNode.class));
        verifyNoMoreInteractions(mockItemRepository);
        verifyNoMoreInteractions(mockPublisherRepository);
        verifyNoMoreInteractions(mockMunicipalityRepository);
        verifyNoMoreInteractions(mockCountyRepository);
        verifyNoMoreInteractions(mockCountryRepository);
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
        ItemNode itemNode = new ItemNode(item.getItemId());
        itemNode.setLocation(new MunicipalityNode(location.getMunicipality()));
        itemNode.setPublisher(publisherNode);

        when(mockItemRepository.findByItemId(item.getItemId())).thenReturn(null);
        when(mockPublisherRepository.findByName(item.getPublisher())).thenReturn(publisherNode);
        when(mockItemRepository.save(any(ItemNode.class))).thenReturn(itemNode);
    }

    private void verifyThatOnlyItemGotSaved(Item item) {
        verify(mockItemRepository).findByItemId(item.getItemId());
        verify(mockPublisherRepository).findByName(item.getPublisher());
        verify(mockItemRepository).save(any(ItemNode.class));
        verifyNoMoreInteractions(mockItemRepository);
        verifyNoMoreInteractions(mockPublisherRepository);
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

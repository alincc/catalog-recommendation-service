package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.CountryNode;
import no.nb.microservices.recommendation.core.graph.model.node.CountyNode;
import no.nb.microservices.recommendation.core.graph.model.node.MunicipalityNode;
import no.nb.microservices.recommendation.core.graph.repository.CountryRepository;
import no.nb.microservices.recommendation.core.graph.repository.CountyRepository;
import no.nb.microservices.recommendation.core.graph.repository.MunicipalityRepository;
import no.nb.microservices.recommendation.model.query.Location;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    private MunicipalityRepository mockMunicipalityRepository;

    @Mock
    private CountyRepository mockCountyRepository;

    @Mock
    private CountryRepository mockCountryRepository;

    @InjectMocks
    private SimpleLocationService simpleLocationService;

    private final Location location = new Location("norway", "nordland", "rana");

    private CountryNode countryNode = new CountryNode(location.getCountry());
    private CountyNode countyNode = new CountyNode(location.getCounty(), countryNode);
    private MunicipalityNode municipalityNode = new MunicipalityNode(location.getMunicipality(), countyNode);

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(mockMunicipalityRepository);
        verifyNoMoreInteractions(mockCountyRepository);
        verifyNoMoreInteractions(mockCountryRepository);
    }

    @Test
    public void whenMunicipalityAndCountyAndCountryIsNotFoundThenCreateMunicipalityAndCountyAndCountry() throws Exception {
        when(mockMunicipalityRepository.save(any(MunicipalityNode.class))).thenReturn(municipalityNode);
        when(mockMunicipalityRepository.findByMunicipality(location.getMunicipality())).thenReturn(null);
        when(mockCountyRepository.findByCounty(location.getCounty())).thenReturn(null);
        when(mockCountryRepository.findByCountry(location.getCountry())).thenReturn(null);

        MunicipalityNode locationNode = simpleLocationService.getLocation(location);
        assertThatCompleteLocationWasReturned(locationNode);

        verify(mockMunicipalityRepository).findByMunicipality(location.getMunicipality());
        verify(mockCountyRepository).findByCounty(location.getCounty());
        verify(mockCountryRepository).findByCountry(location.getCountry());
        verify(mockMunicipalityRepository).save(any(MunicipalityNode.class));
    }

    @Test
    public void whenMunicipalityAndCountyIsNotFoundAndCountryIsFoundThenCreateMunicipalityAndCountyAndFindCountry() throws Exception {
        when(mockMunicipalityRepository.save(any(MunicipalityNode.class))).thenReturn(municipalityNode);
        when(mockMunicipalityRepository.findByMunicipality(location.getMunicipality())).thenReturn(null);
        when(mockCountyRepository.findByCounty(location.getCounty())).thenReturn(null);
        when(mockCountryRepository.findByCountry(location.getCountry())).thenReturn(countryNode);

        MunicipalityNode locationNode = simpleLocationService.getLocation(location);
        assertThatCompleteLocationWasReturned(locationNode);

        verify(mockMunicipalityRepository).findByMunicipality(location.getMunicipality());
        verify(mockCountyRepository).findByCounty(location.getCounty());
        verify(mockCountryRepository).findByCountry(location.getCountry());
        verify(mockMunicipalityRepository).save(any(MunicipalityNode.class));
    }

    @Test
    public void whenMunicipalityIsNotFoundAndCountyIsFoundThenCreateMunicipalityAndFindCounty() throws Exception {
        when(mockMunicipalityRepository.save(any(MunicipalityNode.class))).thenReturn(municipalityNode);
        when(mockMunicipalityRepository.findByMunicipality(location.getMunicipality())).thenReturn(null);
        when(mockCountyRepository.findByCounty(location.getCounty())).thenReturn(countyNode);

        MunicipalityNode locationNode = simpleLocationService.getLocation(location);
        assertThatCompleteLocationWasReturned(locationNode);

        verify(mockMunicipalityRepository).findByMunicipality(location.getMunicipality());
        verify(mockCountyRepository).findByCounty(location.getCounty());
        verify(mockMunicipalityRepository).save(any(MunicipalityNode.class));
    }

    @Test
    public void whenMunicipalityIsFoundThenReturnIt() throws Exception {
        when(mockMunicipalityRepository.save(any(MunicipalityNode.class))).thenReturn(municipalityNode);
        when(mockMunicipalityRepository.findByMunicipality(location.getMunicipality())).thenReturn(municipalityNode);

        MunicipalityNode locationNode = simpleLocationService.getLocation(location);
        assertThatCompleteLocationWasReturned(locationNode);

        verify(mockMunicipalityRepository).findByMunicipality(location.getMunicipality());
        verify(mockMunicipalityRepository).save(any(MunicipalityNode.class));
    }

    private void assertThatCompleteLocationWasReturned(MunicipalityNode locationNode) {
        assertEquals(location.getMunicipality(), locationNode.getMunicipality());
        assertEquals(location.getCounty(), locationNode.getCountyNode().getCounty());
        assertEquals(location.getCountry(), locationNode.getCountyNode().getCountryNode().getCountry());
    }
}

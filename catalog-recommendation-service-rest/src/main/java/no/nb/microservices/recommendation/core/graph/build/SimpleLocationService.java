package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.CountryNode;
import no.nb.microservices.recommendation.core.graph.model.node.CountyNode;
import no.nb.microservices.recommendation.core.graph.model.node.MunicipalityNode;
import no.nb.microservices.recommendation.core.graph.repository.CountryRepository;
import no.nb.microservices.recommendation.core.graph.repository.CountyRepository;
import no.nb.microservices.recommendation.core.graph.repository.MunicipalityRepository;
import no.nb.microservices.recommendation.model.query.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleLocationService implements LocationService {

    private final MunicipalityRepository municipalityRepository;
    private final CountyRepository countyRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public SimpleLocationService(MunicipalityRepository municipalityRepository, CountyRepository countyRepository, CountryRepository countryRepository) {
        this.municipalityRepository = municipalityRepository;
        this.countyRepository = countyRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public MunicipalityNode getLocation(Location location) {
        MunicipalityNode municipalityNode = municipalityRepository.findByMunicipality(location.getMunicipality());
        if(municipalityNode == null) {
            municipalityNode = new MunicipalityNode(location.getMunicipality(), getCountyNodeWithCountryNode(location));
        }
        return municipalityRepository.save(municipalityNode);
    }

    private CountyNode getCountyNodeWithCountryNode(Location location) {
        CountyNode countyNode = countyRepository.findByCounty(location.getCounty());
        if(countyNode == null) {
            countyNode = new CountyNode(location.getCounty(), getCountryNode(location));
        }
        return countyNode;
    }

    private CountryNode getCountryNode(Location location) {
        CountryNode countryNode = countryRepository.findByCountry(location.getCountry());
        if(countryNode == null) {
            countryNode = new CountryNode(location.getCountry());
        }
        return countryNode;
    }
}

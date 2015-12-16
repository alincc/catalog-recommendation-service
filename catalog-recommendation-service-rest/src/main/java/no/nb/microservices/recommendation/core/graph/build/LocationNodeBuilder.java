package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.CountryNode;
import no.nb.microservices.recommendation.core.graph.model.node.CountyNode;
import no.nb.microservices.recommendation.core.graph.model.node.MunicipalityNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.repository.CountryRepository;
import no.nb.microservices.recommendation.core.graph.repository.CountyRepository;
import no.nb.microservices.recommendation.core.graph.repository.MunicipalityRepository;
import no.nb.microservices.recommendation.model.query.Location;

public class LocationNodeBuilder {

    private Location location = null;
    private SessionNode sessionNode;
    private MunicipalityRepository municipalityRepository;
    private CountyRepository countyRepository;
    private CountryRepository countryRepository;

    public LocationNodeBuilder(Location location, MunicipalityRepository municipalityRepository, CountyRepository countyRepository, CountryRepository countryRepository) {
        this.location = location;
        this.municipalityRepository = municipalityRepository;
        this.countyRepository = countyRepository;
        this.countryRepository = countryRepository;
    }

    public LocationNodeBuilder(Location location, SessionNode sessionNode, MunicipalityRepository municipalityRepository, CountyRepository countyRepository, CountryRepository countryRepository) {
        this.location = location;
        this.sessionNode = sessionNode;
        this.municipalityRepository = municipalityRepository;
        this.countyRepository = countyRepository;
        this.countryRepository = countryRepository;
    }

    public MunicipalityNode build() {
        if (location == null) {
            return null;
        }

        if ((sessionNode != null && sessionNode.getLocation() == null) || sessionNode == null) {
            MunicipalityNode oldMunicipalityNode = municipalityRepository.findByMunicipality(this.location.getMunicipality());
            if (oldMunicipalityNode == null) {
                CountyNode countyNode = countyRepository.findByCounty(location.getCounty());
                CountryNode countryNode = countryRepository.findByCountry(location.getCountry());

                if (countryNode == null) {
                    countryNode = new CountryNode(location.getCountry());
                }

                if (countyNode == null) {
                    countyNode = new CountyNode(location.getCounty(), countryNode);
                }

                MunicipalityNode municipalityNode = new MunicipalityNode(location.getMunicipality(), countyNode);

                return municipalityRepository.save(municipalityNode);
            } else {
                return oldMunicipalityNode;
            }
        } else if (sessionNode.getLocation() != null) {
            return sessionNode.getLocation();
        }
        return null;
    }
}


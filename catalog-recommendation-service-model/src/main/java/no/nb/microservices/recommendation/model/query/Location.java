package no.nb.microservices.recommendation.model.query;

public class Location {
    private String municipality;
    private String county;
    private String country;

    public Location() {
    }

    public Location(String country, String county, String municipality) {
        this.municipality = municipality;
        this.county = county;
        this.country = country;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

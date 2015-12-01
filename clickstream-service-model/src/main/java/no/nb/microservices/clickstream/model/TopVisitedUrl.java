package no.nb.microservices.clickstream.model;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@QueryResult
public class TopVisitedUrl {

    @ResultColumn("url")
    private String url;

    @ResultColumn("sesamid")
    private String sesamid;

    @ResultColumn("count")
    private Long numberOfVisits;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSesamid() {
        return sesamid;
    }

    public void setSesamid(String sesamid) {
        this.sesamid = sesamid;
    }

    public Long getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Long numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

}

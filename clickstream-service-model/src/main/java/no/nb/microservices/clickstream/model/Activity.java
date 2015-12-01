package no.nb.microservices.clickstream.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.hateoas.ResourceSupport;

@QueryResult
public class Activity extends ResourceSupport {

    @ResultColumn(value = "graphId")
    private String graphId;

    @ResultColumn(value = "sessionId")
    private String sessionId;

    @ResultColumn(value = "url")
    private String url;

    @ResultColumn(value = "sesamid")
    private String sesamid;

    @ResultColumn(value = "mediatype")
    private String mediatype;

    // Neo4j
    public Activity() {
    }

    @JsonCreator
    public Activity(@JsonProperty("sessionId") String id,
                    @JsonProperty("url") String url,
                    @JsonProperty("sesamid") String sesamid,
                    @JsonProperty("mediatype") String mediatype) {
        this.sessionId = id;
        this.url = url;
        this.sesamid = sesamid;
        this.mediatype = mediatype;
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

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

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }
}

package no.nb.microservices.clickstream.core.graph.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.annotation.QueryResult;

//@QueryResult
//public class NbUrl {
//
//    @ResultColumn(value = "value")
//    private String value;
//
//    @ResultColumn(value = "sesamid")
//    private String sesamid;
//
//    @ResultColumn(value = "mediatype")
//    private String mediatype;
//
//    /**
//     * Neo4j
//     */
//    public NbUrl() {
//    }
//
//    @JsonCreator
//    public NbUrl(@JsonProperty("value") String value, @JsonProperty("sesamid") String sesamid, @JsonProperty("mediatype") String mediatype) {
//        this.value = value;
//        this.sesamid = sesamid;
//        this.mediatype = mediatype;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    public String getSesamid() {
//        return sesamid;
//    }
//
//    public void setSesamid(String sesamid) {
//        this.sesamid = sesamid;
//    }
//
//    public String getMediatype() {
//        return mediatype;
//    }
//
//    public void setMediatype(String mediatype) {
//        this.mediatype = mediatype;
//    }
//}

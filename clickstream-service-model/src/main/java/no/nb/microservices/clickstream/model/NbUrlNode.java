package no.nb.microservices.clickstream.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@TypeAlias(value = "Url")
public class NbUrlNode extends AbstractEntity {

    @Indexed(unique = true)
    private String value;

    private String sesamid;

    private String mediatype;

    @RelatedToVia(elementClass = Visited.class, direction = Direction.INCOMING)
    private Set<Visited> visitors = new HashSet<Visited>();

    /**
     * Neo4j requires empty constructor
     */
    protected NbUrlNode() {
    }

    public NbUrlNode(String value) {
        this.value = value;
    }

    public NbUrlNode(String value, String sesamid, String mediatype) {
        this.value = value;
        this.sesamid = sesamid;
        this.mediatype = mediatype;
    }

    public String getValue() {
        return value;
    }

    public String getSesamid() {
        return sesamid;
    }

    public String getMediatype() {
        return mediatype;
    }
}

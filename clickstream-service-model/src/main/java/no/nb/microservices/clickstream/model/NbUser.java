package no.nb.microservices.clickstream.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@TypeAlias(value = "User")
public class NbUser extends AbstractEntity {

    @Indexed(unique = true)
    private String sessionId;

    @RelatedToVia(elementClass = Visited.class, direction = Direction.OUTGOING)
    private Set<Visited> visited = new HashSet<Visited>();

    /**
     * Neo4j requires empty constructor
     */
    protected NbUser() {
    }

    public NbUser(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Visited visit(NbUrlNode url) {
        Visited v = new Visited(this, url, System.currentTimeMillis());
        visited.add(v);
        return v;
    }
}

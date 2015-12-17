package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.PublisherNode;

public interface PublisherService {
    PublisherNode getPublisher(String publisher);
}

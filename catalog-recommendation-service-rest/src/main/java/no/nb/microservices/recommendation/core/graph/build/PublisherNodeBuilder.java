package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.PublisherNode;
import no.nb.microservices.recommendation.core.graph.repository.PublisherRepository;

public class PublisherNodeBuilder {

    private String name;
    private PublisherRepository publisherRepository;


    public PublisherNodeBuilder(String name, PublisherRepository publisherRepository) {
        this.name = name;
        this.publisherRepository = publisherRepository;
    }

    public PublisherNode build() {
        PublisherNode publisherNode = publisherRepository.findByName(name);

        if (publisherNode == null) {
            publisherNode = publisherRepository.save(new PublisherNode(name));
        }

        return publisherNode;
    }
}

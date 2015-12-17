package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.PublisherNode;
import no.nb.microservices.recommendation.core.graph.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherNodeService implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherNodeService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public PublisherNode getPublisher(String publisher) {
        PublisherNode publisherNode = publisherRepository.findByName(publisher);
        if(publisherNode == null) {
            publisherNode = publisherRepository.save(new PublisherNode(publisher));
        }
        return publisherNode;
    }
}

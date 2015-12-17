package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.PublisherNode;
import no.nb.microservices.recommendation.core.graph.repository.PublisherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PublisherNodeServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherNodeService publisherNodeService;

    private final String publisher = "publisher";

    @Test
    public void whenPublisherDontExistThenCreateIt() throws Exception {
        when(publisherRepository.findByName(publisher)).thenReturn(null);
        when(publisherRepository.save(any(PublisherNode.class))).thenReturn(new PublisherNode(publisher));

        publisherNodeService.getPublisher(publisher);

        verify(publisherRepository).findByName(publisher);
        verify(publisherRepository).save(any(PublisherNode.class));
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    public void whenPublisherExistThenReturnIt() throws Exception {
        when(publisherRepository.findByName(publisher)).thenReturn(new PublisherNode(publisher));

        publisherNodeService.getPublisher(publisher);

        verify(publisherRepository).findByName(publisher);
        verifyNoMoreInteractions(publisherRepository);
    }
}
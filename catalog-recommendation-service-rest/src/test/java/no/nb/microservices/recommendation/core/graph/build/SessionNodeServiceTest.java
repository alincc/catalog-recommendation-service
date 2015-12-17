package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.*;
import no.nb.microservices.recommendation.core.graph.repository.SessionRepository;
import no.nb.microservices.recommendation.model.query.Location;
import no.nb.microservices.recommendation.model.query.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionNodeServiceTest {

    @Mock
    private LocationService locationService;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionNodeService sessionNodeService;

    private UserNode userNode;

    @Before
    public void setUp() throws Exception {
        userNode = new UserNode("user1", 18, "male", Arrays.asList("bil", "motor"));
        SessionNode sessionNode = new SessionNode("session1");
        userNode.addSession(sessionNode);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(locationService);
        verifyNoMoreInteractions(sessionRepository);
    }

    @Test
    public void whenItIsAnonymousUserThenCreateNewSession() throws Exception {
        Session session = new Session("session1");
        session.setLocation(new Location("country", "county", "municipality"));
        when(locationService.getLocation(session.getLocation())).thenReturn(createDefaultLocationNodes());
        when(sessionRepository.save(any(SessionNode.class))).thenReturn(new SessionNode(session.getSessionId()));

        sessionNodeService.getSession(null, session);

        verify(locationService).getLocation(session.getLocation());
        verify(sessionRepository).save(any(SessionNode.class));
    }

    @Test
    public void whenUserHasNewSessionThenCreateNewSession() throws Exception {
        Session session = new Session("session2");
        when(locationService.getLocation(session.getLocation())).thenReturn(createDefaultLocationNodes());
        when(sessionRepository.save(any(SessionNode.class))).thenReturn(new SessionNode(session.getSessionId()));

        sessionNodeService.getSession(userNode, session);

        verify(locationService).getLocation(session.getLocation());
        verify(sessionRepository).save(any(SessionNode.class));
    }

    @Test
    public void whenUserHasOldSessionThenReuseSession() throws Exception {
        Session session = new Session("session1");
        SessionNode sessionNode = sessionNodeService.getSession(userNode, session);
        assertEquals(session.getSessionId(), sessionNode.getSessionId());
    }


    @Test
    public void whenSavingSessionThenCallSessionRepo() throws Exception {
        sessionNodeService.saveSession(new SessionNode("session1"));
        verify(sessionRepository).save(any(SessionNode.class));
    }

    private MunicipalityNode createDefaultLocationNodes() {
        CountryNode countryNode = new CountryNode("country");
        CountyNode countyNode = new CountyNode("county", countryNode);
        return new MunicipalityNode("municipality", countyNode);
    }
}

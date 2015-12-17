package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.UserNode;
import no.nb.microservices.recommendation.core.graph.repository.UserRepository;
import no.nb.microservices.recommendation.model.query.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserNodeServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserNodeService userNodeService;

    @Test
    public void whenAnonymousUserThenReturnNull() throws Exception {
        UserNode userNode = userNodeService.getUser(null);
        assertNull(userNode);
    }

    @Test
    public void whenNewUserThenCreateUser() throws Exception {
        User user = new User("user1");
        when(userRepository.findByUserId(user.getUserId())).thenReturn(null);

        userNodeService.getUser(user);

        verify(userRepository).findByUserId(user.getUserId());
        verify(userRepository).save(any(UserNode.class));
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void whenOldUserThenReturnUser() throws Exception {
        User user = new User("user1");
        when(userRepository.findByUserId(user.getUserId())).thenReturn(new UserNode(user.getUserId()));

        userNodeService.getUser(user);

        verify(userRepository).findByUserId(user.getUserId());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void whenSavingUserThenCallUserRepo() throws Exception {
        userNodeService.save(new UserNode("user1"));
        verify(userRepository).save(any(UserNode.class));
    }
}

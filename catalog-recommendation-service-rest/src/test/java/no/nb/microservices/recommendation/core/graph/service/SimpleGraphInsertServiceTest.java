package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.build.ItemService;
import no.nb.microservices.recommendation.core.graph.build.SearchService;
import no.nb.microservices.recommendation.core.graph.build.SessionService;
import no.nb.microservices.recommendation.core.graph.build.UserService;
import no.nb.microservices.recommendation.core.graph.model.node.*;
import no.nb.microservices.recommendation.model.query.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleGraphInsertServiceTest {


    @Mock
    private UserService userService;

    @Mock
    private SessionService sessionService;

    @Mock
    private ItemService itemService;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private SimpleGraphInsertService simpleGraphInsertService;

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(sessionService);
        verifyNoMoreInteractions(itemService);
        verifyNoMoreInteractions(searchService);
    }

    @Test
    public void whenNotAnonymousUserThenAddItemActionToUserGraph() throws Exception {
        ItemAction itemAction = createDefaultItemAction();
        UserNode userNode = new UserNode(itemAction.getUser().getUserId());
        SessionNode sessionNode = new SessionNode(itemAction.getSession().getSessionId());
        ItemNode itemNode = new ItemNode(itemAction.getItemId());
        SearchNode searchNode = new SearchNode(new SearchQueryNode(itemAction.getQuery()));

        when(userService.getUser(itemAction.getUser())).thenReturn(userNode);
        when(sessionService.getSession(userNode, itemAction.getSession())).thenReturn(sessionNode);
        when(itemService.getItem(itemAction.getItemId())).thenReturn(itemNode);
        when(searchService.getSearchNode(sessionNode, itemNode, itemAction)).thenReturn(searchNode);

        simpleGraphInsertService.addItemAction(itemAction);

        verify(userService).getUser(itemAction.getUser());
        verify(sessionService).getSession(userNode, itemAction.getSession());
        verify(itemService).getItem(itemAction.getItemId());
        verify(searchService).getSearchNode(sessionNode, itemNode, itemAction);
        verify(userService).save(userNode);
    }

    @Test
    public void whenAnonymousUserThenAddItemActionToSessionGraph() throws Exception {
        ItemAction itemAction = createAnonymousItemAction();
        SessionNode sessionNode = new SessionNode(itemAction.getSession().getSessionId());
        ItemNode itemNode = new ItemNode(itemAction.getItemId());
        SearchNode searchNode = new SearchNode(new SearchQueryNode(itemAction.getQuery()));

        when(userService.getUser(null)).thenReturn(null);
        when(sessionService.getSession(null, itemAction.getSession())).thenReturn(sessionNode);
        when(itemService.getItem(itemAction.getItemId())).thenReturn(itemNode);
        when(searchService.getSearchNode(sessionNode, itemNode, itemAction)).thenReturn(searchNode);

        simpleGraphInsertService.addItemAction(itemAction);

        verify(userService).getUser(null);
        verify(sessionService).getSession(null, itemAction.getSession());
        verify(itemService).getItem(itemAction.getItemId());
        verify(searchService).getSearchNode(sessionNode, itemNode, itemAction);
        verify(sessionService).saveSession(sessionNode);
    }

    private ItemAction createDefaultItemAction() {
        ItemAction itemAction = new ItemAction();
        User user = new User();
        user.setUserId("user1");
        itemAction.setUser(user);
        Session session = new Session("session1");
        itemAction.setSession(session);
        return itemAction;
    }

    private ItemAction createAnonymousItemAction() {
        ItemAction itemAction = new ItemAction();
        Session session = new Session("session1");
        itemAction.setSession(session);
        return itemAction;
    }

    @Test
    public void addItemToGraph() throws Exception {
        Item item = createItem();
        simpleGraphInsertService.addItem(item);
        verify(itemService).saveItem(item);
    }

    private Item createItem() {
        String publisher = "publisher";
        String municipality = "municipality";
        String county = "county";
        String country = "country";
        Item item = new Item();
        item.setPublisher(publisher);
        Location location = new Location();
        location.setMunicipality(municipality);
        location.setCounty(county);
        location.setCountry(country);
        item.setLocation(location);
        return item;
    }
}

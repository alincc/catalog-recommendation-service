package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SearchNode;
import no.nb.microservices.recommendation.core.graph.model.node.SearchQueryNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.repository.SearchQueryRepository;
import no.nb.microservices.recommendation.model.query.ItemAction;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchNodeServiceTest {

    @Mock
    private SearchQueryRepository searchQueryRepository;

    @InjectMocks
    private SearchNodeService searchNodeService;

    @Test
    public void whenUserDoNewSearchWithNewQueryThenCreateItBothSearchNodeAndSearchQuery() throws Exception {
        SessionNode sessionNode = createDefaultSessionNode();
        ItemAction itemAction = createDefaultItemAction("query2");
        when(searchQueryRepository.findByQuery(itemAction.getQuery())).thenReturn(null);
        when(searchQueryRepository.save(any(SearchQueryNode.class))).thenReturn(new SearchQueryNode(itemAction.getQuery()));

        SearchNode searchNode = searchNodeService.getSearchNode(sessionNode, null, itemAction);

        assertEquals(itemAction.getQuery(), searchNode.getSearchQuery().getQuery());
        assertEquals(1, searchNode.getVisits().size());
        verify(searchQueryRepository).findByQuery(itemAction.getQuery());
        verify(searchQueryRepository).save(any(SearchQueryNode.class));
        verifyNoMoreInteractions(searchQueryRepository);
    }

    @Test
    public void whenUserDoNewSearchWithSameQueryThenAddItemNodeToOldSearchNode() throws Exception {
        SessionNode sessionNode = createDefaultSessionNode();
        ItemAction itemAction = createDefaultItemAction("query1");
        when(searchQueryRepository.save(any(SearchQueryNode.class))).thenReturn(new SearchQueryNode(itemAction.getQuery()));

        SearchNode searchNode = searchNodeService.getSearchNode(sessionNode, new ItemNode("item2"), itemAction);

        assertEquals(itemAction.getQuery(), searchNode.getSearchQuery().getQuery());
        assertEquals(2, searchNode.getVisits().size());
        verifyNoMoreInteractions(searchQueryRepository);
    }

    @Test
    public void whenUserDoNewSearchWithQueryThatExistsThenReuseIt() throws Exception {
        SessionNode sessionNode = createDefaultSessionNode();
        ItemAction itemAction = createDefaultItemAction("query2");
        when(searchQueryRepository.findByQuery(itemAction.getQuery())).thenReturn(new SearchQueryNode(itemAction.getQuery()));
        when(searchQueryRepository.save(any(SearchQueryNode.class))).thenReturn(new SearchQueryNode(itemAction.getQuery()));

        SearchNode searchNode = searchNodeService.getSearchNode(sessionNode, new ItemNode("item2"), itemAction);

        assertEquals(itemAction.getQuery(), searchNode.getSearchQuery().getQuery());
        assertEquals(1, searchNode.getVisits().size());
        verify(searchQueryRepository).findByQuery(itemAction.getQuery());
        verifyNoMoreInteractions(searchQueryRepository);
    }

    @Test
    @Ignore("Not sure if wanted")
    public void whenUserDoNewSearchWithSameQueryAndVisitSameItemThenDoNothing() throws Exception {
        SessionNode sessionNode = createDefaultSessionNode();
        ItemAction itemAction = createDefaultItemAction("query1");
        when(searchQueryRepository.save(any(SearchQueryNode.class))).thenReturn(new SearchQueryNode(itemAction.getQuery()));

        SearchNode searchNode = searchNodeService.getSearchNode(sessionNode, new ItemNode("item1"), itemAction);

        assertEquals(itemAction.getQuery(), searchNode.getSearchQuery().getQuery());
        assertEquals(1, searchNode.getVisits().size());
        verifyNoMoreInteractions(searchQueryRepository);
    }

    private ItemAction createDefaultItemAction(String query) {
        ItemAction itemAction = new ItemAction();
        itemAction.setQuery(query);
        itemAction.setAction("visit");
        return itemAction;
    }

    private SessionNode createDefaultSessionNode() {
        SessionNode sessionNode = new SessionNode("session1");
        SearchQueryNode searchQueryNode = new SearchQueryNode("query1");
        SearchNode searchNode = new SearchNode(searchQueryNode);
        searchNode.addAction(new ItemNode("item1"), "visit");
        sessionNode.addSearch(searchNode);
        return sessionNode;
    }

}
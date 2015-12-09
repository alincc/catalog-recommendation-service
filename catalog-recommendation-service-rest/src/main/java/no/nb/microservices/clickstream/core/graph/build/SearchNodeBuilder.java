package no.nb.microservices.clickstream.core.graph.build;

import no.nb.microservices.clickstream.core.graph.model.node.ItemNode;
import no.nb.microservices.clickstream.core.graph.model.node.SearchNode;
import no.nb.microservices.clickstream.core.graph.model.node.SearchQueryNode;
import no.nb.microservices.clickstream.core.graph.model.node.SessionNode;
import no.nb.microservices.clickstream.core.graph.repository.SearchQueryRepository;
import no.nb.microservices.clickstream.model.ItemAction;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class SearchNodeBuilder {

    private SearchQueryRepository searchQueryRepository;
    private SessionNode session;
    private ItemNode item;
    private String action;
    private String query;

    public SearchNodeBuilder(ItemAction itemAction, SessionNode session, ItemNode item, SearchQueryRepository searchQueryRepository) {
        this.query = itemAction.getQuery();
        this.action = itemAction.getAction();
        this.session = session;
        this.item = item;
        this.searchQueryRepository = searchQueryRepository;
    }

    public SearchNode build() {
        SearchNode searchNode = null;

        if (!StringUtils.isEmpty(query)) {
            Optional<SearchNode> searchNodeOptional = session.getSearches().stream()
                    .filter(q -> q.getSearchQuery().getQuery().equalsIgnoreCase(query)).findFirst();
            searchNode = (searchNodeOptional.isPresent()) ? searchNodeOptional.get() : new SearchNode();

            SearchQueryNode searchQuery = (searchNode.getSearchQuery() == null) ? searchQueryRepository.findByQuery(query) : searchNode.getSearchQuery();
            if (searchQuery == null) {
                searchQuery = searchQueryRepository.save(new SearchQueryNode(query));
            }

            searchNode.setSearchQuery(searchQuery);
            searchNode.addAction(item, action);
        }

        return searchNode;
    }
}

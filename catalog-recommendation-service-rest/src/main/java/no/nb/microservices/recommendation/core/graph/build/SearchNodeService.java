package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SearchNode;
import no.nb.microservices.recommendation.core.graph.model.node.SearchQueryNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.core.graph.repository.SearchQueryRepository;
import no.nb.microservices.recommendation.model.query.ItemAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchNodeService implements SearchService {

    private final SearchQueryRepository searchQueryRepository;

    @Autowired
    public SearchNodeService(SearchQueryRepository searchQueryRepository) {
        this.searchQueryRepository = searchQueryRepository;
    }

    @Override
    public SearchNode getSearchNode(SessionNode sessionNode, ItemNode itemNode, ItemAction itemAction) {
        SearchNode searchNode;
        Optional<SearchNode> searchNodeOptional = sessionNode.getSearches()
                .stream()
                .filter(s -> s.getSearchQuery().getQuery().equalsIgnoreCase(itemAction.getQuery()))
                .findFirst();

        if(searchNodeOptional.isPresent()) {
            searchNode = searchNodeOptional.get();
        } else {
            SearchQueryNode searchQueryNode = searchQueryRepository.findByQuery(itemAction.getQuery());
            if(searchQueryNode == null) {
                searchQueryNode = searchQueryRepository.save(new SearchQueryNode(itemAction.getQuery()));
            }
            searchNode = new SearchNode(searchQueryNode);
        }

        searchNode.addAction(itemNode, itemAction.getAction());
        return searchNode;
    }
}

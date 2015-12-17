package no.nb.microservices.recommendation.core.graph.build;

import no.nb.microservices.recommendation.core.graph.model.node.ItemNode;
import no.nb.microservices.recommendation.core.graph.model.node.SearchNode;
import no.nb.microservices.recommendation.core.graph.model.node.SessionNode;
import no.nb.microservices.recommendation.model.query.ItemAction;

public interface SearchService {
    SearchNode getSearchNode(SessionNode sessionNode, ItemNode itemNode, ItemAction itemAction);
}

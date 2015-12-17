package no.nb.microservices.recommendation.core.graph.service;

import no.nb.microservices.recommendation.core.graph.model.query.RecommendationQuery;
import no.nb.microservices.recommendation.core.graph.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SimpleGraphQueryService implements GraphQueryService {

    private final ItemRepository itemRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SearchRepository searchRepository;
    private final PublisherRepository publisherRepository;
    private final SearchQueryRepository searchQueryRepository;

    @Autowired
    public SimpleGraphQueryService(ItemRepository itemRepository, SessionRepository sessionRepository, UserRepository userRepository,
                                   SearchRepository searchRepository, PublisherRepository publisherRepository,
                                   SearchQueryRepository searchQueryRepository) {
        this.itemRepository = itemRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.searchRepository = searchRepository;
        this.publisherRepository = publisherRepository;
        this.searchQueryRepository = searchQueryRepository;
    }

    @Override
    public Collection<RecommendationQuery> findWhatOtherHaveVisited(String itemId) {
        return itemRepository.findWhatOtherHaveVisited(itemId);
    }

    @Override
    public Collection<RecommendationQuery> findMostVisitedItems(long fromDate, long toDate, int limit, String mediaType) {
        if (StringUtils.isEmpty(mediaType)) {
            return itemRepository.findMostVisited(fromDate, toDate, limit);
        }
        else {
            return itemRepository.findMostVisited(fromDate, toDate, mediaType, limit);
        }
    }
}

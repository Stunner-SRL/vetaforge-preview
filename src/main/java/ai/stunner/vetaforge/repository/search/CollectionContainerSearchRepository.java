package ai.stunner.vetaforge.repository.search;

import ai.stunner.vetaforge.domain.CollectionContainer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link CollectionContainer} entity.
 */
public interface CollectionContainerSearchRepository extends ElasticsearchRepository<CollectionContainer, String> {}

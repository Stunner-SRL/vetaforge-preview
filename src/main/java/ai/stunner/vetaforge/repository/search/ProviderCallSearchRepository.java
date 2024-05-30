package ai.stunner.vetaforge.repository.search;

import ai.stunner.vetaforge.domain.CollectionContainer;
import ai.stunner.vetaforge.domain.ProviderCall;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link CollectionContainer} entity.
 */
public interface ProviderCallSearchRepository extends ElasticsearchRepository<ProviderCall, String> {}

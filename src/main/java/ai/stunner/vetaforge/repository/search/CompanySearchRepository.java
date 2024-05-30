package ai.stunner.vetaforge.repository.search;

import ai.stunner.vetaforge.domain.Company;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Company} entity.
 */
public interface CompanySearchRepository extends ElasticsearchRepository<Company, String> {}

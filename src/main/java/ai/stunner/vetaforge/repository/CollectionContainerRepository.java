package ai.stunner.vetaforge.repository;

import ai.stunner.vetaforge.domain.CollectionContainer;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CollectionContainer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollectionContainerRepository extends MongoRepository<CollectionContainer, String> {
    List<CollectionContainer> findAllByFactoringRequestId(String factoringRequestId);
    List<CollectionContainer> findAllByFactoringRequestIdAndType(String factoringRequestId, String type);
    List<CollectionContainer> findAllByFactoringRequestIdAndTaskId(String factoringRequestId, String taskId);
    List<CollectionContainer> findAllByType(String type);

    //    List<CollectionContainer> findByValuesFieldInAndValuesValueIn(String fieldName, String value);
//    @Query("{'attributes.age' : ?0}")
//    List<CollectionContainer> findByValuesFieldInAndValuesValueIn(Set<String> fields, Set<String> values);
}

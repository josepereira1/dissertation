package com.example.ecommerce.saga.outbound.mongo.sagadefinition;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SagaDefinitionDAO extends MongoRepository<SagaDefinition, String> {

    @Query(value = "{ 'id' : ?0}", fields = "{authentication: 1, roles: 1, jsonSchema: 1, httpMethod: 1}")
    Optional<SagaDefinition> findAuthenticationAndRolesAndJsonSchema(String id);
}

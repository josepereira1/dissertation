package com.example.ecommerce.saga.core.ports.out.sagadefinition;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import java.util.Optional;

public interface IQuerySagaDefinitionRepository {
    Optional<SagaDefinition> findById(String id);
    Optional<SagaDefinition> findAuthenticationAndRolesAndJsonSchema(String id);
}

package com.example.ecommerce.saga.core.ports.out.sagadefinition;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;

public interface ICommandSagaDefinitionRepository {
    SagaDefinition save(SagaDefinition sagaDefinition);
    void deleteById(String id);
}

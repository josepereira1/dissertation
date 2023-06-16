package com.example.ecommerce.saga.core.ports.in.sagadefinition;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionNotExistsException;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;

public interface IQuerySagaDefinition {
    SagaDefinition readSagaDefinition(String id) throws EcommerceBusinessLogicException;
    SagaDefinition readSagaDefinitionAuthenticationAndHttpMethodAndRolesAndJsonSchema(String id) throws EcommerceBusinessLogicException;
}

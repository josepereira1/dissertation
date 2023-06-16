package com.example.ecommerce.saga.core.ports.in.sagadefinition;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;

public interface ICommandSagaDefinition {
    SagaDefinition createSagaDefinition(SagaDefinition sagaDefinition) throws EcommerceBusinessLogicException;
    SagaDefinition updateSagaDefinition(String id, SagaDefinition sagaDefinition) throws EcommerceBusinessLogicException;
    void deleteSagaDefinition(String id) throws EcommerceBusinessLogicException;
}

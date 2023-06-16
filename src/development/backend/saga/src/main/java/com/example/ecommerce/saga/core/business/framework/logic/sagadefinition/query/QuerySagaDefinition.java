package com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.query;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.query.features.ReadSagaDefinition;
import com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.query.features.ReadSagaDefinitionAuthenticationAndRolesAndJsonSchema;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.in.sagadefinition.IQuerySagaDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuerySagaDefinition implements IQuerySagaDefinition {

    @Autowired
    private ReadSagaDefinition readSagaDefinition;

    @Autowired
    private ReadSagaDefinitionAuthenticationAndRolesAndJsonSchema readSagaDefinitionAuthenticationAndRolesAndJsonSchema;

    @Override
    public SagaDefinition readSagaDefinition(String id) throws EcommerceBusinessLogicException {
        return readSagaDefinition.readSagaDefinition(id);
    }

    @Override
    public SagaDefinition readSagaDefinitionAuthenticationAndHttpMethodAndRolesAndJsonSchema(String id) throws EcommerceBusinessLogicException {
        return readSagaDefinitionAuthenticationAndRolesAndJsonSchema.readSagaDefinitionAuthenticationAndRolesAndJsonSchema(id);
    }
}

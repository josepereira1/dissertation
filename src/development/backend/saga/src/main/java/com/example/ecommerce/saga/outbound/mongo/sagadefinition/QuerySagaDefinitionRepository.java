package com.example.ecommerce.saga.outbound.mongo.sagadefinition;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.IQuerySagaDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QuerySagaDefinitionRepository implements IQuerySagaDefinitionRepository {

    @Autowired
    private SagaDefinitionDAO sagaDefinitionDAO;

    @Override
    public Optional<SagaDefinition> findById(String id) {
        return sagaDefinitionDAO.findById(id);
    }

    @Override
    public Optional<SagaDefinition> findAuthenticationAndRolesAndJsonSchema(String id) {
        return sagaDefinitionDAO.findAuthenticationAndRolesAndJsonSchema(id);
    }
}

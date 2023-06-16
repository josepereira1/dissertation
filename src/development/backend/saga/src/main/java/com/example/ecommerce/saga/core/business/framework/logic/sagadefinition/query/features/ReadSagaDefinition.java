package com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.query.features;

import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionNotExistsException;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.IQuerySagaDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadSagaDefinition {

    @Autowired
    private IQuerySagaDefinitionRepository querySagaDefinitionRepository;

    public SagaDefinition readSagaDefinition(String id) throws SagaDefinitionNotExistsException {
        Optional<SagaDefinition> optional = querySagaDefinitionRepository.findById(id);
        if(!optional.isPresent()) throw SagaDefinitionNotExistsException.builder().id(id).build();
        return optional.get();
    }
}

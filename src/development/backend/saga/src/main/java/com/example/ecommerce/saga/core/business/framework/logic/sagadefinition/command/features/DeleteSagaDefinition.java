package com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command.features;

import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionNotExistsException;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.ICommandSagaDefinitionRepository;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.IQuerySagaDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeleteSagaDefinition {

    @Autowired
    private ICommandSagaDefinitionRepository commandSagaDefinitionRepository;

    @Autowired
    private IQuerySagaDefinitionRepository querySagaDefinitionRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteSagaDefinition(String id) throws SagaDefinitionNotExistsException {
        Optional<SagaDefinition> optional = querySagaDefinitionRepository.findById(id);
        if(!optional.isPresent()) throw SagaDefinitionNotExistsException.builder().id(id).build();
        commandSagaDefinitionRepository.deleteById(id);
    }
}

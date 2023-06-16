package com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command.features;

import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.BadDefinitionException;
import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionAlreadyExistsException;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.ICommandSagaDefinitionRepository;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.IQuerySagaDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateSagaDefinition {

    @Autowired
    private ICommandSagaDefinitionRepository commandSagaDefinitionRepository;

    @Autowired
    private IQuerySagaDefinitionRepository querySagaDefinitionRepository;

    @Transactional(rollbackFor = Exception.class)
    public SagaDefinition createSagaDefinition(SagaDefinition sagaDefinition) throws BadDefinitionException, SagaDefinitionAlreadyExistsException {
        Optional<SagaDefinition> optional = querySagaDefinitionRepository.findById(sagaDefinition.getId());
        if(optional.isPresent()) throw SagaDefinitionAlreadyExistsException.builder().id(sagaDefinition.getId()).build();
        CommonSagaDefinition.checkSagaDefinition(sagaDefinition);
        return commandSagaDefinitionRepository.save(sagaDefinition);
    }
}

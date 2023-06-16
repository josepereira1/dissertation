package com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command.features;

import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.BadDefinitionException;
import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionNotExistsException;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.HttpMethod;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.Participant;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.ICommandSagaDefinitionRepository;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.IQuerySagaDefinitionRepository;
import com.example.ecommerce.saga.inbound.rest.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateSagaDefinition {

    @Autowired
    private ICommandSagaDefinitionRepository commandSagaDefinitionRepository;

    @Autowired
    private IQuerySagaDefinitionRepository querySagaDefinitionRepository;

    public SagaDefinition updateSagaDefinition(String id, SagaDefinition updatedSagaDefinition) throws SagaDefinitionNotExistsException, BadDefinitionException {
        Optional<SagaDefinition> optional = querySagaDefinitionRepository.findById(id);
        if(!optional.isPresent()) throw SagaDefinitionNotExistsException.builder().id(id).build();
        SagaDefinition currentSagaDefinition = optional.get();
        if(updatedSagaDefinition.getExchange() != null) currentSagaDefinition.setExchange(updatedSagaDefinition.getExchange());
        if(updatedSagaDefinition.getSuccessfullyMessage() != null) currentSagaDefinition.setSuccessfullyMessage(updatedSagaDefinition.getSuccessfullyMessage());
        if(updatedSagaDefinition.getSuccessfullyCode() != null) currentSagaDefinition.setSuccessfullyCode(updatedSagaDefinition.getSuccessfullyCode());
        if(updatedSagaDefinition.getCommitRoutingKey() != null) currentSagaDefinition.setCommitRoutingKey(updatedSagaDefinition.getCommitRoutingKey());
        if(updatedSagaDefinition.getCommitMethodName() != null) currentSagaDefinition.setCommitMethodName(updatedSagaDefinition.getCommitMethodName());
        if(updatedSagaDefinition.getHttpMethod() != null) currentSagaDefinition.setHttpMethod(updatedSagaDefinition.getHttpMethod());
        if(updatedSagaDefinition.getRoles() != null) currentSagaDefinition.setRoles(updatedSagaDefinition.getRoles());
        if(updatedSagaDefinition.getOutputParams() != null) currentSagaDefinition.setOutputParams(updatedSagaDefinition.getOutputParams());
        if(updatedSagaDefinition.getJsonSchema() != null) currentSagaDefinition.setJsonSchema(updatedSagaDefinition.getJsonSchema());
        if(updatedSagaDefinition.getParticipants() != null) currentSagaDefinition.setParticipants(updatedSagaDefinition.getParticipants());
        CommonSagaDefinition.checkSagaDefinition(currentSagaDefinition);
        return commandSagaDefinitionRepository.save(currentSagaDefinition);
    }
}

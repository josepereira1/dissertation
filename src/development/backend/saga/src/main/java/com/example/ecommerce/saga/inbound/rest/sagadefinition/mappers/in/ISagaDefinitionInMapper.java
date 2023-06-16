package com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.Participant;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.CreateSagaDefinitionParticipantDTO;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.CreateSagaDefinitionSagaDefinitionDTO;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.UpdateSagaDefinitionParticipantDTO;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.UpdateSagaDefinitionSagaDefinitionDTO;
import java.util.HashMap;
import java.util.Map;

public interface ISagaDefinitionInMapper {
    static SagaDefinition toSagaDefinition(CreateSagaDefinitionSagaDefinitionDTO sagaDefinition){
        SagaDefinition tmp1 = new SagaDefinition();
        tmp1.setId(sagaDefinition.getId());
        tmp1.setExchange(sagaDefinition.getExchange());
        tmp1.setSuccessfullyMessage(sagaDefinition.getSuccessfullyMessage());
        tmp1.setSuccessfullyCode(sagaDefinition.getSuccessfullyCode());
        tmp1.setCommitRoutingKey(sagaDefinition.getCommitRoutingKey());
        tmp1.setCommitMethodName(sagaDefinition.getCommitMethodName());
        tmp1.setHttpMethod(sagaDefinition.getHttpMethod());
        tmp1.setRoles(sagaDefinition.getRoles());
        tmp1.setOutputParams(sagaDefinition.getOutputParams());
        tmp1.setJsonSchema(sagaDefinition.getJsonSchema());
        Map<Integer, Participant> participants = new HashMap<>();
        for(Map.Entry<Integer, CreateSagaDefinitionParticipantDTO> entry : sagaDefinition.getParticipants().entrySet())
            participants.put(entry.getKey(), toParticipant(entry.getValue()));
        tmp1.setParticipants(participants);
        return tmp1;
    }

    static SagaDefinition toSagaDefinition(UpdateSagaDefinitionSagaDefinitionDTO sagaDefinition){
        SagaDefinition tmp1 = new SagaDefinition();
        tmp1.setExchange(sagaDefinition.getExchange());
        tmp1.setSuccessfullyMessage(sagaDefinition.getSuccessfullyMessage());
        tmp1.setSuccessfullyCode(sagaDefinition.getSuccessfullyCode());
        tmp1.setCommitRoutingKey(sagaDefinition.getCommitRoutingKey());
        tmp1.setCommitMethodName(sagaDefinition.getCommitMethodName());
        tmp1.setHttpMethod(sagaDefinition.getHttpMethod());
        tmp1.setRoles(sagaDefinition.getRoles());
        tmp1.setOutputParams(sagaDefinition.getOutputParams());
        tmp1.setJsonSchema(sagaDefinition.getJsonSchema());
        Map<Integer, Participant> participants = new HashMap<>();
        for(Map.Entry<Integer, UpdateSagaDefinitionParticipantDTO> entry : sagaDefinition.getParticipants().entrySet())
            participants.put(entry.getKey(), toParticipant(entry.getValue()));
        tmp1.setParticipants(participants);
        return tmp1;
    }

    static Participant toParticipant(CreateSagaDefinitionParticipantDTO participant) {
        Participant tmp1 = new Participant();
        tmp1.setServiceName(participant.getServiceName());
        tmp1.setRoutingKey(participant.getRoutingKey());
        tmp1.setLocalTransaction(participant.getLocalTransaction());
        tmp1.setCompensatingTransaction(participant.getCompensatingTransaction());
        return tmp1;
    }

    static Participant toParticipant(UpdateSagaDefinitionParticipantDTO participant) {
        Participant tmp1 = new Participant();
        tmp1.setServiceName(participant.getServiceName());
        tmp1.setRoutingKey(participant.getRoutingKey());
        tmp1.setLocalTransaction(participant.getLocalTransaction());
        tmp1.setCompensatingTransaction(participant.getCompensatingTransaction());
        return tmp1;
    }
}

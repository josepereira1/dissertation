package com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.out;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.Participant;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.CreateSagaDefinitionParticipantDTO;
import com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos.CreateSagaDefinitionSagaDefinitionDTO;
import java.util.HashMap;
import java.util.Map;

public interface ISagaDefinitionOutMapper {
    static CreateSagaDefinitionSagaDefinitionDTO toSagaDefinitionDTO(SagaDefinition sagaDefinition){
        CreateSagaDefinitionSagaDefinitionDTO tmp1 = new CreateSagaDefinitionSagaDefinitionDTO();
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
        Map<Integer, CreateSagaDefinitionParticipantDTO> participants = new HashMap<>();
        for(Map.Entry<Integer, Participant> entry : sagaDefinition.getParticipants().entrySet())
            participants.put(entry.getKey(), toParticipant(entry.getValue()));
        tmp1.setParticipants(participants);
        return tmp1;
    }

    static CreateSagaDefinitionParticipantDTO toParticipant(Participant participant) {
        CreateSagaDefinitionParticipantDTO tmp1 = new CreateSagaDefinitionParticipantDTO();
        tmp1.setServiceName(participant.getServiceName());
        tmp1.setRoutingKey(participant.getRoutingKey());
        tmp1.setLocalTransaction(participant.getLocalTransaction());
        tmp1.setCompensatingTransaction(participant.getCompensatingTransaction());
        return tmp1;
    }
}

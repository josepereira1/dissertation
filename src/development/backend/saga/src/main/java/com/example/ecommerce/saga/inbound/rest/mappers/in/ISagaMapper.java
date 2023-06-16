package com.example.ecommerce.saga.inbound.rest.mappers.in;

import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import com.example.ecommerce.saga.inbound.rest.mappers.in.dtos.SagaDTO;

public interface ISagaMapper {
    static SagaDTO toSagaDTO(Saga saga){
        SagaDTO tmp1 = new SagaDTO();
        tmp1.setId(saga.getId());
        tmp1.setName(saga.getName());
        tmp1.setStart(saga.getStart());
        tmp1.setEnd(saga.getEnd());
        tmp1.setStatus(saga.getStatus());
        tmp1.setMessage(saga.getMessage());
        tmp1.setErrorService(saga.getErrorService());
        tmp1.setCurrentService(saga.getCurrentService());
        tmp1.setCode(saga.getCode());
        tmp1.setUserId(saga.getEntityId());
        tmp1.setPosition(saga.getPosition());
        tmp1.setSize(saga.getSize());
        tmp1.setInput(saga.getInput());
        tmp1.setOutput(saga.getOutput());
        return tmp1;
    }
}

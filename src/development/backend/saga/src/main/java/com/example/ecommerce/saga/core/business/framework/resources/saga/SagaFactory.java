package com.example.ecommerce.saga.core.business.framework.resources.saga;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SagaFactory implements ISagaFactory{
    public Saga factory(String name, Integer size){
        Saga saga = new Saga();
        saga.setName(name);
        saga.setStart(LocalDateTime.now());
        saga.setPosition(-1);
        saga.setSize(size);
        saga.setStatus(SagaStatus.PENDING);
        return saga;
    }
}

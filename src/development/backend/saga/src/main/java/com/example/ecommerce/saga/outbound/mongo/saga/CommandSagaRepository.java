package com.example.ecommerce.saga.outbound.mongo.saga;

import com.example.ecommerce.saga.core.ports.out.saga.ICommandSagaRepository;
import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSagaRepository implements ICommandSagaRepository {

    @Autowired
    private SagaDAO sagaDAO;

    public Saga save(Saga saga){
        return sagaDAO.save(saga);
    }
}

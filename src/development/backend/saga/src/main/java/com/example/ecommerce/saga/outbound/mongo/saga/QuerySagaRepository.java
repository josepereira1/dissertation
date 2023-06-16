package com.example.ecommerce.saga.outbound.mongo.saga;

import com.example.ecommerce.saga.core.ports.out.saga.IQuerySagaRepository;
import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class QuerySagaRepository implements IQuerySagaRepository {

    @Autowired
    private SagaDAO sagaDAO;

    @Override
    public Optional<Saga> findSagaById(String id){
        return sagaDAO.findById(id);
    }

    @Override
    public Collection<Saga> findAll(){
        return sagaDAO.findAll();
    }

    @Override
    public Collection<Saga> findAllByEntityId(String entityId) {
        return sagaDAO.findAllByEntityId(entityId);
    }
}

package com.example.ecommerce.saga.core.business.framework.logic.saga.query.features;

import com.example.ecommerce.saga.core.business.exceptions.auth.UnauthorizedException;
import com.example.ecommerce.saga.core.business.framework.exceptions.SagaWithThatIdNotExistsException;
import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import com.example.ecommerce.saga.core.ports.out.saga.IQuerySagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GetSaga {

    @Autowired
    private IQuerySagaRepository querySagaRepository;

    public Saga getSaga(boolean authorization, String tokenSubject, String id) throws SagaWithThatIdNotExistsException, UnauthorizedException {
        Optional<Saga> optional = querySagaRepository.findSagaById(id);
        if(!optional.isPresent()) throw SagaWithThatIdNotExistsException.builder().id(id).build();
        Saga saga = optional.get();
        if(authorization && !saga.getEntityId().equals(tokenSubject)) throw UnauthorizedException.builder().build();
        return saga;
    }
}

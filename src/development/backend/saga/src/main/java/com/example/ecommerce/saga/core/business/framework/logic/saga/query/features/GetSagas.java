package com.example.ecommerce.saga.core.business.framework.logic.saga.query.features;

import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import com.example.ecommerce.saga.core.ports.out.saga.IQuerySagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class GetSagas {

    @Autowired
    private IQuerySagaRepository querySagaRepository;

    public Collection<Saga> getSagas(String entityId) {
        return querySagaRepository.findAllByEntityId(entityId);
    }
}

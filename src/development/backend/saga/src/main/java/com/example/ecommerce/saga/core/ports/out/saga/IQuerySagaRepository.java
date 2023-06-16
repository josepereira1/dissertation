package com.example.ecommerce.saga.core.ports.out.saga;

import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import java.util.Collection;
import java.util.Optional;

public interface IQuerySagaRepository {
    Optional<Saga> findSagaById(String id);
    Collection<Saga> findAll();
    Collection<Saga> findAllByEntityId(String entityId);
}

package com.example.ecommerce.saga.core.ports.in.saga;

import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import java.util.Collection;

public interface IQueryEntity {
    Collection<Saga> getEntitySagas(String entityId);
}

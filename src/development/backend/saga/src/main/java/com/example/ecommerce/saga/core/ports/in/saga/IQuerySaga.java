package com.example.ecommerce.saga.core.ports.in.saga;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import java.util.Collection;

public interface IQuerySaga {
    Collection<Saga> getSagas(String entityId);
    Saga getSaga(boolean authorization, String tokenSubject, String id) throws EcommerceBusinessLogicException;
}

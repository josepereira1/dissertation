package com.example.ecommerce.saga.core.business.framework.logic.saga.query;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.business.framework.logic.saga.query.features.GetSagas;
import com.example.ecommerce.saga.core.business.framework.logic.saga.query.features.GetSaga;
import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import com.example.ecommerce.saga.core.ports.in.saga.IQuerySaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class QuerySaga implements IQuerySaga {

    @Autowired
    private GetSaga getSaga;

    @Autowired
    private GetSagas getSagas;

    @Override
    public Collection<Saga> getSagas(String entityId) {
        return getSagas.getSagas(entityId);
    }

    @Override
    public Saga getSaga(boolean authorization, String tokenSubject, String id) throws EcommerceBusinessLogicException {
        return getSaga.getSaga(authorization, tokenSubject, id);

    }
}

package com.example.ecommerce.saga.inbound.rest.saga.query;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.ports.in.saga.IQuerySaga;
import com.example.ecommerce.saga.inbound.rest.mappers.in.ISagaMapper;
import com.example.ecommerce.saga.inbound.rest.security.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
public class QuerySagaController implements IQuerySagaController {

    @Autowired
    private IQuerySaga querySaga;

    @Autowired
    private Authentication authentication;

    @Override
    public ResponseEntity getSagas(String token) {
        return ResponseEntity.ok(querySaga.getSagas(authentication.authenticateAndGetClaims(token).getSubject()).stream().map(ISagaMapper::toSagaDTO).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity getSaga(String token, String id) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(querySaga.getSaga(true, authentication.authenticateAndGetClaims(token).getSubject(), id));
    }
}

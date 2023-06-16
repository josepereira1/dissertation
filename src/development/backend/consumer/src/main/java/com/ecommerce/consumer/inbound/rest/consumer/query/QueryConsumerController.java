package com.ecommerce.consumer.inbound.rest.consumer.query;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.consumer.core.business.logic.authentication.command.util.ISecurity;
import com.ecommerce.consumer.core.ports.in.consumer.IQueryConsumer;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.out.IConsumerOutMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryConsumerController implements IQueryConsumerController {

    @Autowired
    private IQueryConsumer queryConsumer;

    @Autowired
    private ISecurity security;

    @Override
    public ResponseEntity readConsumer(String token, String id) throws EcommerceBusinessLogicException {
        Claims claims = security.authenticate(token);
        if(!claims.getSubject().equals(id)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(IConsumerOutMapper.toConsumerDTO(queryConsumer.readConsumer(id)));
    }
}

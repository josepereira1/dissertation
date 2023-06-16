package com.ecommerce.consumer.inbound.rest.consumer.command;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.consumer.core.business.logic.authentication.command.util.ISecurity;
import com.ecommerce.consumer.core.ports.in.consumer.ICommandConsumer;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.IConsumerInMapper;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.CreateConsumerFeatureClientDTO;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.UpdateConsumerFeatureClientDTO;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.out.IConsumerOutMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandClientController implements ICommandClientController {

    @Autowired
    private ICommandConsumer commandClient;

    @Autowired
    private ISecurity security;

    @Override
    public ResponseEntity createConsumer(CreateConsumerFeatureClientDTO consumer) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(IConsumerOutMapper.toConsumerDTO(commandClient.createConsumer(IConsumerInMapper.toConsumer(consumer))));
    }

    @Override
    public ResponseEntity updateConsumer(String token, String id, UpdateConsumerFeatureClientDTO consumer) throws EcommerceBusinessLogicException{
        Claims claims = security.authenticate(token);
        if(!claims.getSubject().equals(id)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(IConsumerOutMapper.toConsumerDTO(commandClient.updateConsumer(id, IConsumerInMapper.toConsumer(consumer))));
    }
}

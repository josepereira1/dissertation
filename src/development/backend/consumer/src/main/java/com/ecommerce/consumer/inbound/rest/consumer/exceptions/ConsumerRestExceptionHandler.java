package com.ecommerce.consumer.inbound.rest.consumer.exceptions;

import com.ecommerce.consumer.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.consumer.core.business.exceptions.consumer.ConsumerAlreadyExistsException;
import com.ecommerce.consumer.inbound.rest.Response;
import com.ecommerce.consumer.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ConsumerRestExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(ConsumerAlreadyExistsException.class)
    public ResponseEntity exp(ConsumerAlreadyExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CONSUMER_ALREADY_EXISTS).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity exp(UnauthorizedException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.AUTHORIZATION_FAIL).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }
}

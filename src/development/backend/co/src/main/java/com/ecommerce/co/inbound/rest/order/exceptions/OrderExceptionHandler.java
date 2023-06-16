package com.ecommerce.co.inbound.rest.order.exceptions;

import com.ecommerce.co.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.co.core.business.exceptions.order.CantCreateOrderWithouProductsException;
import com.ecommerce.co.core.business.exceptions.order.OrderNotExistsException;
import com.ecommerce.co.inbound.rest.Response;
import com.ecommerce.co.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.security.SignatureException;
import java.time.LocalDateTime;

@ControllerAdvice
public class OrderExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(OrderNotExistsException.class)
    public ResponseEntity exp(OrderNotExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.ORDER_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CantCreateOrderWithouProductsException.class)
    public ResponseEntity exp(CantCreateOrderWithouProductsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CANT_CREATE_ORDER_WITHOUT_PRODUCTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity exp(UnauthorizedException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.UNAUTHORIZED).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity exp(SignatureException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.UNAUTHORIZED).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }
}

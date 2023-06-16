package com.ecommerce.sc.inbound.rest.shoppingcart.exceptions;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithThatIdNotExistException;
import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartAlreadyExistsException;
import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartNotExistsException;
import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.inbound.rest.Response;
import com.ecommerce.sc.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ShoppingCartExceptionHandler {

    @Value("${service}")
    private String service;

    @Autowired
    private ILogs logs;

    @ExceptionHandler(ShoppingCartNotExistsException.class)
    protected ResponseEntity exp(ShoppingCartNotExistsException e){
        e.printStackTrace();
        logs.logError(e.getMessage());
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.SHOPPING_CART_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShoppingCartAlreadyExistsException.class)
    protected ResponseEntity exp(ShoppingCartAlreadyExistsException e){
        e.printStackTrace();
        logs.logError(e.getMessage());
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.SHOPPING_CART_ALREADY_EXISTS).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductWithThatIdNotExistException.class)
    protected ResponseEntity exp(ProductWithThatIdNotExistException e){
        e.printStackTrace();
        logs.logError(e.getMessage());
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}

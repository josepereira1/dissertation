package com.ecommerce.inventory.inbound.rest.product.exceptions;

import com.ecommerce.inventory.core.business.exceptions.product.ProductAlreadyExistsException;
import com.ecommerce.inventory.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.inventory.inbound.rest.Response;
import com.ecommerce.inventory.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ProductRestExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(ProductAlreadyExistsException.class)
    protected ResponseEntity exp(ProductAlreadyExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_ALREADY_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductWithThatIdNotExistException.class)
    protected ResponseEntity exp(ProductWithThatIdNotExistException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_NOT_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}

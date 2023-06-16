package com.ecommerce.qp.inbound.rest.product.exceptions;

import com.ecommerce.qp.core.business.exceptions.product.ProductIsBlockedException;
import com.ecommerce.qp.core.business.exceptions.product.ProductWithIdAlreadyExists;
import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.exceptions.product.ProductNotVisibleException;
import com.ecommerce.qp.inbound.rest.Response;
import com.ecommerce.qp.inbound.rest.ResponseStatus;
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

    @ExceptionHandler(ProductWithIdAlreadyExists.class)
    public ResponseEntity exp(ProductWithIdAlreadyExists e){
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_WITH_ID_ALREADY_EXISTS).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity exp(ProductNotExistException e){
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_WITH_THAT_ID_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotVisibleException.class)
    public ResponseEntity exp(ProductNotVisibleException e){
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_UNAVAILABLE).message(e.getMessage()).build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ProductIsBlockedException.class)
    public ResponseEntity exp(ProductIsBlockedException e){
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_IS_BLOCKED).message(e.getMessage()).build(), HttpStatus.FORBIDDEN);
    }
}

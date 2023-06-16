package com.ecommerce.cc.inbound.adapters.rest.product.exceptions;

import com.ecommerce.cc.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cc.inbound.adapters.rest.Response;
import com.ecommerce.cc.inbound.adapters.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ProductRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(ProductWithThatIdNotExistException.class)
    protected ResponseEntity exp(ProductWithThatIdNotExistException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PRODUCT_WITH_THAT_ID_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}

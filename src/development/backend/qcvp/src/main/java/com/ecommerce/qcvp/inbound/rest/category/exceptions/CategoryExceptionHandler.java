package com.ecommerce.qcvp.inbound.rest.category.exceptions;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.inbound.rest.Response;
import com.ecommerce.qcvp.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class CategoryExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(CategoryNotExistsException.class)
    public ResponseEntity exp1(CategoryNotExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CATEGORY_TREE_NOT_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    /*@ExceptionHandler(PageAndProductsPerPageParametersMustBeGreaterThanZeroException.class)
    public ResponseEntity exp(PageAndProductsPerPageParametersMustBeGreaterThanZeroException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.PAGE_AND_PRODUCTS_PER_PAGE_MUST_BE_GREATER_THAN_ZERO).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(VisibilityParameterIsInvalidException.class)
    public ResponseEntity exp(VisibilityParameterIsInvalidException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.VISIBILITY_PARAMETER_IS_INVALID).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}

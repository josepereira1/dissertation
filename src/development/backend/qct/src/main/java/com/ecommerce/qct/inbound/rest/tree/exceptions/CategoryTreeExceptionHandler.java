package com.ecommerce.qct.inbound.rest.tree.exceptions;

import com.ecommerce.qct.core.business.exceptions.tree.CategoryTreeNotExistsException;
import com.ecommerce.qct.inbound.rest.Response;
import com.ecommerce.qct.inbound.rest.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class CategoryTreeExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(CategoryTreeNotExistsException.class)
    public ResponseEntity exp1(CategoryTreeNotExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CATEGORY_TREE_NOT_EXISTS_EXCEPTION).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);

    }
}

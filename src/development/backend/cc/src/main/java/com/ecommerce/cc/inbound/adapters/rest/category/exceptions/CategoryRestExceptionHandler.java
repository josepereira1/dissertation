package com.ecommerce.cc.inbound.adapters.rest.category.exceptions;

import com.ecommerce.cc.core.business.exceptions.category.*;
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
public class CategoryRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${service}")
    private String service;

    @ExceptionHandler(CanOnlyCreateRootCategoryException.class)
    protected ResponseEntity exp1(CanOnlyCreateRootCategoryException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CAN_ONLY_CREATE_ROOT_CATEGORY).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNotExistException.class)
    protected ResponseEntity exp2(CategoryNotExistException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CATEGORY_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    protected ResponseEntity exp3(CategoryAlreadyExistException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CATEGORY_ALREADY_EXISTS).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNameIsInvalidException.class)
    protected ResponseEntity exp4(CategoryNameIsInvalidException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.CATEGORY_NAME_IS_INVALID).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RootCategoryNotExistsException.class)
    protected ResponseEntity exp6(RootCategoryNotExistsException e){
        e.printStackTrace();
        return new ResponseEntity(Response.builder().service(service).timestamp(LocalDateTime.now()).status(ResponseStatus.ERROR).appCode(Response.ROOT_CATEGORY_NOT_EXISTS).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}

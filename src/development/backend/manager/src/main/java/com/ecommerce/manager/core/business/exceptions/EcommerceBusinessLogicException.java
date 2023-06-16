package com.ecommerce.manager.core.business.exceptions;

public class EcommerceBusinessLogicException extends Exception{
    public EcommerceBusinessLogicException(){
        super();
    }

    public EcommerceBusinessLogicException(String message){
        super(message);
    }
}
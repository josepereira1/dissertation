package com.ecommerce.sc.inbound.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Response {

    public final static String PREFIX = "";
    public final static Integer BASE_CODE = 80000;

    public static final String SUCCESS = PREFIX + BASE_CODE;

    //  SHOPPING CART
    public static final String SHOPPING_CART_NOT_EXISTS = PREFIX + (BASE_CODE + 1);
    public static final String SHOPPING_CART_ALREADY_EXISTS = PREFIX + (BASE_CODE + 2);

    //  PRODUCT
    public static final String PRODUCT_NOT_EXISTS = PREFIX + (BASE_CODE + 3);
    public static final String PRODUCT_ALREADY_EXISTS = PREFIX + (BASE_CODE + 4);

    //  GENERIC
    public static final String UNAUTHORIZED = PREFIX + (BASE_CODE + 5);
    public static final String PARAMETERS_IN_FAULT = PREFIX + (BASE_CODE + 6);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



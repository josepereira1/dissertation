package com.ecommerce.co.inbound.rest;

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

    //  ORDER
    public static final String ORDER_NOT_EXISTS = PREFIX + (BASE_CODE + 1);
    public static final String CANT_CREATE_ORDER_WITHOUT_PRODUCTS = PREFIX + (BASE_CODE + 2);

    //  GENERIC
    public static final String UNAUTHORIZED = PREFIX + (BASE_CODE + 3);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



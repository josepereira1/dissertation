package com.ecommerce.cc.inbound.adapters.rest;

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
    public final static Integer BASE_CODE = 30000;

    public static final String SUCCESS = PREFIX + BASE_CODE;

    //  CATEGORY
    public static final String CAN_ONLY_CREATE_ROOT_CATEGORY = PREFIX + (BASE_CODE + 1);
    public static final String CATEGORY_ALREADY_EXISTS = PREFIX + (BASE_CODE + 2);
    public static final String CATEGORY_NAME_ALREADY_EXISTS =  PREFIX + (BASE_CODE + 3);
    public static final String CATEGORY_NAME_IS_INVALID = PREFIX + (BASE_CODE + 4);
    public static final String CATEGORY_NOT_EXISTS = PREFIX + (BASE_CODE + 5);
    public static final String ROOT_CATEGORY_NOT_EXISTS = PREFIX + (BASE_CODE + 6);

    //  PRODUCT
    public static final String PRODUCT_WITH_ID_ALREADY_EXISTS = PREFIX + (BASE_CODE + 7);
    public static final String PRODUCT_WITH_THAT_ID_NOT_EXISTS = PREFIX + (BASE_CODE + 8);

    //  GENERIC
    public static final String BAD_REQUEST = PREFIX + (BASE_CODE + 9);

    //  VERSION
    public final static String VERSION_NOT_EXISTS = PREFIX + (BASE_CODE + 10);
    public final static String VERSION_ALREADY_EXISTS = PREFIX + (BASE_CODE + 11);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



package com.ecommerce.qp.inbound.rest;

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
    public final static Integer BASE_CODE = 40000;

    public static final String SUCCESS = PREFIX + BASE_CODE;

    //  PRODUCT
    public static final String PRODUCT_WITH_ID_ALREADY_EXISTS = PREFIX + (BASE_CODE + 1);
    public static final String PRODUCT_WITH_THAT_ID_NOT_EXISTS = PREFIX + (BASE_CODE + 2);
    public static final String PRODUCT_UNAVAILABLE = PREFIX + (BASE_CODE + 3);
    public static final String PRODUCT_IS_BLOCKED = PREFIX + (BASE_CODE + 4);

    //  AUTH
    public static final String AUTHENTICATION_FAIL = PREFIX + (BASE_CODE + 5);


    //  GENERIC
    public static final String BAD_REQUEST = PREFIX + (BASE_CODE + 6);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



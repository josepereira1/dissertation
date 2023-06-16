package com.ecommerce.cp.inbound.rest;

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
    public final static Integer BASE_CODE = 20000;

    public static final String SUCCESS = PREFIX + BASE_CODE;

    //  PRODUCT
    public static final String PRODUCT_WITH_THAT_ID_NOT_EXISTS = PREFIX + (BASE_CODE + 1);
    public static final String PRODUCT_ALREADY_EXISTS = PREFIX + (BASE_CODE + 2);
    public static final String ARGUMENTS_IN_FAULT = PREFIX + (BASE_CODE + 3);

    //  GENERIC
    public static final String BAD_REQUEST = PREFIX + "-" + (BASE_CODE + 4);

    //  VERSION
    public final static String VERSION_NOT_EXISTS = PREFIX + (BASE_CODE + 5);
    public final static String VERSION_ALREADY_EXISTS = PREFIX + (BASE_CODE + 6);

    public String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



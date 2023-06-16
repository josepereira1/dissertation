package com.ecommerce.consumer.inbound.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {

    public final static String PREFIX = "";
    public final static Integer BASE_CODE = 90000;

    public static final String SUCCESS = PREFIX + BASE_CODE;

    //  CLIENT
    public static final String CONSUMER_ALREADY_EXISTS = PREFIX + (BASE_CODE + 1);

    //  AUTHENTICATION
    public static final String AUTHENTICATION_FAIL = PREFIX + (BASE_CODE + 2);
    public static final String UNAUTHORIZED = PREFIX + (BASE_CODE + 3);


    //  AUTHORIZATION
    public static final String AUTHORIZATION_FAIL = PREFIX + (BASE_CODE + 4);

    //  GENERIC
    public static final String INTERNAL_SERVER_ERROR = PREFIX + (BASE_CODE + 5);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



package com.ecommerce.manager.inbound.rest;

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

    //  EMPLOYEE
    public static final String EMPLOYEE_ALREADY_EXISTS = PREFIX + (BASE_CODE + 1);

    //  AUTHENTICATION
    public static final String AUTHENTICATION_FAIL = PREFIX + (BASE_CODE + 2);

    //  AUTHORIZATION
    public static final String UNAUTHORIZED = PREFIX + (BASE_CODE + 3);

    //  GENERIC
    public static final String INTERNAL_SERVER_ERROR = PREFIX + (BASE_CODE + 4);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



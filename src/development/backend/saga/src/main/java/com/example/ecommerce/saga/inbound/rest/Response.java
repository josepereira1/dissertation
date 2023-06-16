package com.example.ecommerce.saga.inbound.rest;

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
    public final static Integer BASE_CODE = 10000;

    public static final String SUCCESS = PREFIX + (BASE_CODE + 1);

    //  SAGA
    public static final String SAGA_NOT_EXISTS_EXCEPTION = PREFIX + (BASE_CODE + 2);

    //  SAGAS (GENERIC)
    public static final String METHOD_NOT_ALLOWED_HERE_EXCEPTION = PREFIX + (BASE_CODE + 3);

    //  SAGA DEFINITION
    public static final String SAGA_DEFINITION_NOT_EXISTS_EXCEPTION = PREFIX + (BASE_CODE + 4);
    public static final String SAGA_DEFINITION_ALREADY_EXCEPTION = PREFIX + (BASE_CODE + 5);
    public static final String BAD_DEFINITION_EXCEPTION = PREFIX + (BASE_CODE + 6);

    //  GENERIC
    public static final String JSON_PROCESSING = PREFIX + (BASE_CODE + 7);
    public static final String UNAUTHORIZED = PREFIX + (BASE_CODE + 8);
    public static final String BAD_REQUEST = PREFIX + (BASE_CODE + 9);
    public static final String INTERNAL_SERVER_ERROR = PREFIX + (BASE_CODE + 10);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



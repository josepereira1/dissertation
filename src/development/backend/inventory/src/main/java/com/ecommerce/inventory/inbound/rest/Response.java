package com.ecommerce.inventory.inbound.rest;

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
    public final static Integer BASE_CODE = 60000;

    public static final String SUCCESS = PREFIX + BASE_CODE;

    //  CATEGORY
    public static final String PRODUCT_ALREADY_EXISTS_EXCEPTION = PREFIX + (BASE_CODE + 1);
    public static final String PRODUCT_NOT_EXISTS_EXCEPTION = PREFIX + (BASE_CODE + 2);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



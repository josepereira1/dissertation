package com.ecommerce.qcvp.inbound.rest;

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

    public final static String SUCCESS = PREFIX + BASE_CODE;

    //  CATEGORY
    public final static String CATEGORY_TREE_NOT_EXISTS_EXCEPTION = PREFIX + (BASE_CODE + 1);

    //  INPUTS
    public final static String PAGE_AND_PRODUCTS_PER_PAGE_MUST_BE_GREATER_THAN_ZERO = PREFIX + (BASE_CODE + 2);
    public final static String VISIBILITY_PARAMETER_IS_INVALID = PREFIX + (BASE_CODE + 3);

    //  AUTH
    public final static String AUTHENTICATION_FAIL = PREFIX + (BASE_CODE + 4);

    //  VERSION
    public final static String VERSION_NOT_EXISTS = PREFIX + (BASE_CODE + 5);
    public final static String VERSION_ALREADY_EXISTS = PREFIX + (BASE_CODE + 6);

    private String service;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private ResponseStatus status;
    private String appCode;
    private String message;
    private Object data;
}



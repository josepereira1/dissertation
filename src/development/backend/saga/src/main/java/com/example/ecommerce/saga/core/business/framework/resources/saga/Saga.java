package com.example.ecommerce.saga.core.business.framework.resources.saga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Saga {

    private String id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private SagaStatus status;
    private String currentService;
    private String errorService;
    private String message;
    private Integer code;
    private String entityId;
    private Integer position;
    private Integer size;
    private Object input;
    private Object output;
}

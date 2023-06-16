package com.example.ecommerce.saga.inbound.rest.mappers.in.dtos;

import com.example.ecommerce.saga.core.business.framework.resources.saga.SagaStatus;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SagaDTO {
    private String id;

    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private SagaStatus status;
    private String message;
    private String errorService;
    private String currentService;
    private Integer code;
    private String userId;
    private Integer position;
    private Integer size;
    private Object input;
    private Object output;
}

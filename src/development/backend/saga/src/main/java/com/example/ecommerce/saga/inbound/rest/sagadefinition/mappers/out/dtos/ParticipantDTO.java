package com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantDTO {
    private String serviceName;
    private String routingKey;
    private String localTransaction;
    private String compensatingTransaction;
}

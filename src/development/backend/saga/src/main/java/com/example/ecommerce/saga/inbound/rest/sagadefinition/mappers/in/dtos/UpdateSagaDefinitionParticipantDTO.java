package com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateSagaDefinitionParticipantDTO {
    private String serviceName;
    private String routingKey;
    private String localTransaction;
    private String compensatingTransaction;
}

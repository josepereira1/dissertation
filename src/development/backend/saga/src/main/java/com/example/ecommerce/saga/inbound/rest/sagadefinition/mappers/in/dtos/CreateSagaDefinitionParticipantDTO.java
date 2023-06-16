package com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateSagaDefinitionParticipantDTO {
    @NotBlank
    private String serviceName;
    @NotBlank
    private String routingKey;
    @NotBlank
    private String localTransaction;
    @NotBlank
    private String compensatingTransaction;
}

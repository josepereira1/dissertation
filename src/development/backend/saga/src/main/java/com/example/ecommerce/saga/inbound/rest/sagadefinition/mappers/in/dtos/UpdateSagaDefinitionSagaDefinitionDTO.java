package com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.in.dtos;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.HttpMethod;
import com.example.ecommerce.saga.inbound.rest.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateSagaDefinitionSagaDefinitionDTO {
    private String exchange;
    private String successfullyMessage;
    private Integer successfullyCode;
    private String commitRoutingKey;
    private String commitMethodName;
    private HttpMethod httpMethod;
    private List<Role> roles;
    private List<String> outputParams;
    private Object jsonSchema;
    private Map<Integer, UpdateSagaDefinitionParticipantDTO> participants;
}

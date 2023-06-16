package com.example.ecommerce.saga.inbound.rest.sagadefinition.mappers.out.dtos;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.HttpMethod;
import com.example.ecommerce.saga.inbound.rest.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SagaDefinitionDTO {

    @NotBlank
    private String id;
    @NotBlank
    private String exchange;
    @NotBlank
    private String successfullyMessage;
    @NotNull
    private Integer successfullyCode;
    @NotBlank
    private String commitRoutingKey;
    @NotBlank
    private String commitMethodName;
    @NotNull
    private HttpMethod httpMethod;
    @NotNull
    private List<Role> roles;
    @NotNull
    private List<String> outputParams;
    @NotNull
    private Object jsonSchema;
    @NotNull
    private Map<Integer, ParticipantDTO> participants;
}

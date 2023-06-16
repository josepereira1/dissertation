package com.example.ecommerce.saga.core.business.framework.resources.sagadefinition;

import com.example.ecommerce.saga.inbound.rest.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SagaDefinition {

    @Id
    private String id;
    private String exchange;
    private String successfullyMessage;
    private Integer successfullyCode;
    private String commitRoutingKey;
    private String commitMethodName;
    private HttpMethod httpMethod;
    private List<Role> roles;
    private List<String> outputParams;
    private Object jsonSchema;
    private Map<Integer, Participant> participants;
}

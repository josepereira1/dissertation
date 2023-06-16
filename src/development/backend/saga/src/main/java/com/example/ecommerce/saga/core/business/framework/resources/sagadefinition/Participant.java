package com.example.ecommerce.saga.core.business.framework.resources.sagadefinition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Participant {
    private String serviceName;
    private String routingKey;
    private String localTransaction;
    private String compensatingTransaction;
}

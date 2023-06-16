package com.example.ecommerce.saga.core.business.framework.resources.saga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SagaMetadata {
    private String sagaId;
    private String token;
}

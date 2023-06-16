package com.ecommerce.qp.core.business.saga.utils.resources;

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

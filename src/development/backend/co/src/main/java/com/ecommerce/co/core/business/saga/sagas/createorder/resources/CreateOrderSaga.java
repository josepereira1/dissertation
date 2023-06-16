package com.ecommerce.co.core.business.saga.sagas.createorder.resources;

import com.ecommerce.co.core.business.saga.utils.resources.SagaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderSaga {
    @Id
    private String id;

    //  SAGA
    private String sagaId;
    private SagaStatus sagaStatus;

    //  DATA
    private String orderId;
}

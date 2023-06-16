package com.ecommerce.inventory.core.business.saga.sagas.createproduct.resources;

import com.ecommerce.inventory.core.business.saga.utils.resources.SagaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CreateProductSaga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //  SAGA
    private String sagaId;
    private SagaStatus sagaStatus;

    //  DATA
    private String productId;
}

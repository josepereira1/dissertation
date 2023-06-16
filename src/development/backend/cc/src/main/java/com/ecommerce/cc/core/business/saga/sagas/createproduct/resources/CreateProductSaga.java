package com.ecommerce.cc.core.business.saga.sagas.createproduct.resources;

import com.ecommerce.cc.core.business.saga.utils.resources.SagaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
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

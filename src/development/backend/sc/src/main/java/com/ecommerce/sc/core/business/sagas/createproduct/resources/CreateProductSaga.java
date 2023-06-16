package com.ecommerce.sc.core.business.sagas.createproduct.resources;

import com.ecommerce.sc.core.business.sagas.utils.resources.SagaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

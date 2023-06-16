package com.ecommerce.inventory.core.business.saga.sagas.createorder.resources;

import com.ecommerce.inventory.core.business.saga.utils.resources.SagaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CreateOrderSaga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //  SAGA
    private String sagaId;
    private SagaStatus sagaStatus;

    //  DATA
    @OneToMany(mappedBy="createOrderSaga")
    private Set<CreateOrderSagaProductBackup> productsBackup;
}

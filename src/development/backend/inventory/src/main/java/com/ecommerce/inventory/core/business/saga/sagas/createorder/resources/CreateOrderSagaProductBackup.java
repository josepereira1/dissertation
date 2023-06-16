package com.ecommerce.inventory.core.business.saga.sagas.createorder.resources;

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
public class CreateOrderSagaProductBackup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //  DATA
    private String productId;
    private Double quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_backup_id", referencedColumnName = "id")
    private CreateOrderSaga createOrderSaga;
}

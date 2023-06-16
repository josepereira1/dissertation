package com.ecommerce.inventory.core.business.saga.sagas.updateproduct.resources;

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
public class UpdateProductSagaProductBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    private String sku;
    private String ean;
    private String pn;

    @OneToOne(mappedBy = "productBackup")
    private UpdateProductSaga updateProductSaga;
}

package com.ecommerce.sc.core.business.sagas.updateproduct.resources;

import com.ecommerce.sc.core.business.resources.Visibility;
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
    private String name;
    private String currency;
    private Double finalPrice;
    @Column(columnDefinition = "TEXT")
    private String links;
    private Visibility visibility;
    private String sku;
    private String ean;
    private String pn;

    private Character countermeasure;

    @OneToOne(mappedBy = "productBackup")
    private UpdateProductSaga updateProductSaga;
}

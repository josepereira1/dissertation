package com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources;

import com.ecommerce.cp.core.business.resources.product.Visibility;
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
    @Column(columnDefinition = "TEXT")
    private String shortDetails;
    @Column(columnDefinition = "TEXT")
    private String longDetails;
    private String currency;
    private Double initialPrice;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double vatPercentage;
    private Double amountInVat;
    private Double finalPrice;
    private Double shipping;
    @Column(columnDefinition = "TEXT")
    private String links;
    private Visibility visibility;
    private String sku;
    private String ean;
    private String pn;

    @OneToOne(mappedBy = "productBackup")
    private UpdateProductSaga productBackup;
}

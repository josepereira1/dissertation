package com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources;

import com.ecommerce.cc.core.business.resources.product.StockStatus;
import com.ecommerce.cc.core.business.resources.product.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UpdateProductSagaProductBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String shortDetails;
    private String currency;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double finalPrice;
    private StockStatus stockStatus;
    @Column(columnDefinition = "TEXT")
    private String links;
    private Visibility visibility;
    private String pn;

    @OneToOne(mappedBy = "productBackup")
    private UpdateProductSaga updateProductSaga;

    @Override
    public String toString() {
        return "UpdateProductSagaProductBackup{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", shortDetails='" + shortDetails + '\'' +
                ", currency='" + currency + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", amountInDiscount=" + amountInDiscount +
                ", finalPrice=" + finalPrice +
                ", stockStatus=" + stockStatus +
                ", visibility=" + visibility +
                ", pn='" + pn + '\'' +
                //", categories=" + categories +
                '}';
    }
}

package com.ecommerce.sc.core.business.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {
    @Id
    private String id;
    private String name;
    private String currency;
    private Double finalPrice;
    @Column(columnDefinition = "TEXT")
    private String links;
    private Visibility visibility;
    private String sku;
    private String ean;
    private String pn;

    private String owner;
    private CounterMeasure countermeasure;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = {}, fetch = FetchType.LAZY)
    private Set<ShoppingCartAndProduct> shoppingCarts;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", finalPrice=" + finalPrice +
                ", sku='" + sku + '\'' +
                ", ean='" + ean + '\'' +
                ", pn='" + pn + '\'' +
                '}';
    }
}

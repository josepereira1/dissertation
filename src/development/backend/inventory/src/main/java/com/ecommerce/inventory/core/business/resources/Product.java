package com.ecommerce.inventory.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product {

    @Id
    private String id;
    private String sku;
    private String ean;
    private String pn;
    private StockUnit stockUnit;
    private Double stockQuantity;
    private StockStatus stockStatus;
    private Boolean deleted;
    private String owner;
    private CounterMeasure countermeasure;
}

package com.ecommerce.cp.core.business.resources.product;

import com.ecommerce.cp.core.business.resources.CounterMeasure;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product implements Comparable {

    @Id
    private String id;
    private String name;
    //private Object shortDetails;
    @Column(columnDefinition = "TEXT")
    private String shortDetails;

    //private Object longDetails;
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

    private StockStatus stockStatus;

    //private Object links;
    @Column(columnDefinition = "TEXT")
    private String links;

    private Visibility visibility;

    private String sku;             //  SKU (Stock keeping unit)
    private String ean;             //  EAN (International Article Number)
    private String pn;              //  PN  (brand id)

    private String owner;
    @JsonIgnore
    private CounterMeasure countermeasure;

    @Override
    public int compareTo(Object o) {
        Product product = (Product) o;
        if(this.getName().equals(product.getName())) return 1;
        else if(this.getName().equals(product.getName())) return -1;
        else return 0;
    }
}

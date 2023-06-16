package com.ecommerce.qp.core.business.resources.product;

import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
public class Product implements Comparable {

    @Id
    private String id;
    private String name;
    private Object shortDetails;
    private Object longDetails;
    private String currency;
    private Double initialPrice;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double vatPercentage;
    private Double amountInVat;
    private Double finalPrice;
    private Double shipping;
    private StockStatus stockStatus;
    private Object links;
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

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shortDetails='" + shortDetails + '\'' +
                ", longDetails=" + longDetails +
                ", currency='" + currency + '\'' +
                ", initialPrice=" + initialPrice +
                ", discountPercentage=" + discountPercentage +
                ", amountInDiscount=" + amountInDiscount +
                ", vatPercentage=" + vatPercentage +
                ", amountInVat=" + amountInVat +
                ", finalPrice=" + finalPrice +
                ", shipping=" + shipping +
                ", stockStatus=" + stockStatus +
                ", sku='" + sku + '\'' +
                ", ean='" + ean + '\'' +
                ", pn='" + pn + '\'' +
                ", countermeasure=" + countermeasure +
                '}';
    }
}

package com.ecommerce.qct.core.business.resources.category.category;

import com.ecommerce.qct.core.business.resources.CounterMeasure;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private String id;
    private String name;
    private Object shortDetails;
    private String currency;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double finalPrice;
    private StockStatus stockStatus;
    private Object links;
    @JsonIgnore
    private Visibility visibility;
    private String pn;
    @JsonIgnore
    private String owner;
    @JsonIgnore
    private CounterMeasure countermeasure;
}

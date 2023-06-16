package com.ecommerce.co.core.business.resources;

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
    private Integer quantity;

    private String currency;
    private Double initialPrice;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double vatPercentage;
    private Double amountInVat;
    private Double finalPrice;
    private Double shipping;

    private String sku;
    private String ean;
    private String pn;
}

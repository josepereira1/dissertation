package com.ecommerce.qp.core.business.saga.sagas.createorder.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private String id;
    private Integer quantity;
    private String name;
    private String currency;
    private Double initialPrice;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double vatPercentage;
    private Double amountInVat;
    private Double finalPrice;
    private Double shipping;
    private String sku;             //  SKU (Stock keeping unit)
    private String ean;             //  EAN (International Article Number)
    private String pn;              //  PN  (brand id)
}
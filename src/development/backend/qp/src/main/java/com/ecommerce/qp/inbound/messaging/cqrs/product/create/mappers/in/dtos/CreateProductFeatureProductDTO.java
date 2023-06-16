package com.ecommerce.qp.inbound.messaging.cqrs.product.create.mappers.in.dtos;

import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.ecommerce.qp.core.business.resources.product.StockStatus;
import com.ecommerce.qp.core.business.resources.product.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductFeatureProductDTO {
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
}

package com.ecommerce.cp.outbound.messaging.cqrs.product.mappers.out.dtos;

import com.ecommerce.cp.core.business.resources.CounterMeasure;
import com.ecommerce.cp.core.business.resources.product.StockStatus;
import com.ecommerce.cp.core.business.resources.product.Visibility;
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
    private CounterMeasure countermeasure;
}

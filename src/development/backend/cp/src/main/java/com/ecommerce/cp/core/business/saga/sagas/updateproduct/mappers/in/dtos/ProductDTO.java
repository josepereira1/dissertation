package com.ecommerce.cp.core.business.saga.sagas.updateproduct.mappers.in.dtos;

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
    private String sagaId;
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
    private Object links;
    private Visibility visibility;
    private String sku;
    private String ean;
    private String pn;
}

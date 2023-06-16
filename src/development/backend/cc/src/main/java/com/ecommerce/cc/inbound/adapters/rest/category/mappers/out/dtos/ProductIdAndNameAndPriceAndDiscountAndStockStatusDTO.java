package com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.dtos;

import com.ecommerce.cc.core.business.resources.product.StockStatus;
import com.ecommerce.cc.core.business.resources.product.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductIdAndNameAndPriceAndDiscountAndStockStatusDTO {
    private String id;
    private String name;
    private Object shortDetails;
    private String currency;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double finalPrice;
    private StockStatus stockStatus;
    private Object links;
    private Visibility visibility;
    private String pn;
}
package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.updateproductincategories.mapper.in.dtos;

import com.ecommerce.qcvp.core.business.resources.CounterMeasure;
import com.ecommerce.qcvp.core.business.resources.category.category.StockStatus;
import com.ecommerce.qcvp.core.business.resources.category.category.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateProductInCategoriesDTO {
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
    private String owner;
    private CounterMeasure countermeasure;
    private Collection<Long> currentProductCategories;
    private Collection<Long> addCategories;
    private Collection<Long> removeCategories;
}

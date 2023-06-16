package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in.dtos;

import com.ecommerce.qcap.core.business.resources.CounterMeasure;
import com.ecommerce.qcap.core.business.resources.category.category.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductInCategoriesDTO {
    private String id;
    private String name;
    private Object shortDetails;
    private String currency;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double finalPrice;
    private Object links;
    private Visibility visibility;
    private String pn;
    private Collection<Long> categories;
    private String owner;
    private CounterMeasure countermeasure;
}

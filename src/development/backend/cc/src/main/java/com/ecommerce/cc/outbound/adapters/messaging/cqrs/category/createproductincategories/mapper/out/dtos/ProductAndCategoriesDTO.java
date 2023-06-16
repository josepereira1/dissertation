package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.createproductincategories.mapper.out.dtos;

import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.product.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductAndCategoriesDTO {
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
    private String owner;
    private CounterMeasure countermeasure;
    private Collection<Long> categories;
}

package com.ecommerce.cc.core.business.saga.sagas.createproduct.mappers.dtos;

import com.ecommerce.cc.core.business.resources.product.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductAndCategoryIdsDTO {
    private String id;
    private String name;
    private Object shortDetails;
    private String currency;
    private Double initialPrice;
    private Double discountPercentage;
    private Double amountInDiscount;
    private Double finalPrice;
    private Object links;
    private Visibility visibility;
    private String pn;
    //private Set<Long> categories;
}

package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.deleteproductincategories.mapper.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductIdAndCategoriesDTO {
    private String id;
    private Collection<Long> categoryIds;
}

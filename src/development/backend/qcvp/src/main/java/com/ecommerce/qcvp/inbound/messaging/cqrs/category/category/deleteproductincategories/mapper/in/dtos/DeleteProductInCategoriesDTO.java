package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.deleteproductincategories.mapper.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeleteProductInCategoriesDTO {
    private String id;
    private Collection<Long> categoryIds;
}

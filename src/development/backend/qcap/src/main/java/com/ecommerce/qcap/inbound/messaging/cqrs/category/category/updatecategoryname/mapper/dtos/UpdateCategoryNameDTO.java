package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updatecategoryname.mapper.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateCategoryNameDTO {
    private Long id;
    private String name;
}

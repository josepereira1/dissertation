package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.createcategories.mapper.in.dtos;

import com.ecommerce.qcvp.core.business.resources.CounterMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateCategoriesDTO {
    private Long id;
    private String name;
    private String owner;
    private CounterMeasure countermeasure;
}

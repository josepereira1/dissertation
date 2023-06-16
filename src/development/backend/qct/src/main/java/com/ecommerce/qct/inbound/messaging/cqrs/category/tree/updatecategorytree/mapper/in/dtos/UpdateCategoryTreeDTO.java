package com.ecommerce.qct.inbound.messaging.cqrs.category.tree.updatecategorytree.mapper.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateCategoryTreeDTO {
    private Long id;
    private String name;
    private List<UpdateCategoryTreeDTO> childCategories;
}

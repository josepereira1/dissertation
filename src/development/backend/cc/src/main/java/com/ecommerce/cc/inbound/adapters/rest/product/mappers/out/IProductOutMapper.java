package com.ecommerce.cc.inbound.adapters.rest.product.mappers.out;

import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.inbound.adapters.rest.product.mappers.out.dtos.CategoryDTO;

public interface IProductOutMapper {

    static CategoryDTO toCategoryDTO(Category category){
        CategoryDTO tmp = new CategoryDTO();
        tmp.setId(category.getId());
        tmp.setName(category.getName());
        return tmp;
    }
}

package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories.mapper.in;

import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories.mapper.in.dtos.CreateCategoriesDTO;

public interface ICategoryInMapper {
    static Category toCategory(CreateCategoriesDTO category){
        Category tmp1 = new Category();
        tmp1.setId(category.getId());
        tmp1.setName(category.getName());
        tmp1.setOwner(category.getOwner());
        tmp1.setCountermeasure(category.getCountermeasure());
        return tmp1;
    }
}

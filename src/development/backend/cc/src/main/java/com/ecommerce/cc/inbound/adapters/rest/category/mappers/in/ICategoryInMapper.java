package com.ecommerce.cc.inbound.adapters.rest.category.mappers.in;

import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.in.dtos.CategoryNameDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryInMapper {
    static Category toCategory(CategoryNameDTO category){
        Category tmp = new Category();
        tmp.setName(category.getName());
        return tmp;
    }

    static Category toCategory(String name){
        Category tmp = new Category();
        tmp.setName(name);
        return tmp;
    }
}

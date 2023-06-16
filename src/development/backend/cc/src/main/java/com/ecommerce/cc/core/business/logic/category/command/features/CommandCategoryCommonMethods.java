package com.ecommerce.cc.core.business.logic.category.command.features;

import com.ecommerce.cc.core.business.exceptions.category.CategoryNameIsInvalidException;
import com.ecommerce.cc.core.business.resources.category.Category;
import org.springframework.stereotype.Service;

@Service
public class CommandCategoryCommonMethods {

    public static boolean childCategoryAlreadyExist(Category parentCategory, Category childCategory) throws CategoryNameIsInvalidException {
        if(parentCategory == null || childCategory == null) throw CategoryNameIsInvalidException.builder().categoryName(childCategory.getName()).build();
        for(Category elem : parentCategory.getChildCategories()) if (elem.getName().equals(childCategory.getName())) return true;
        return false;
    }
}

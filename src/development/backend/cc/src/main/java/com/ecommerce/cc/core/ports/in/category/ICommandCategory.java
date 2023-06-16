package com.ecommerce.cc.core.ports.in.category;

import com.ecommerce.cc.core.business.exceptions.*;
import com.ecommerce.cc.core.business.resources.category.Category;
import java.io.IOException;
import java.util.Collection;

public interface ICommandCategory {
    Category createCategory(Category category) throws EcommerceBusinessLogicException;
    Collection<Category> updateCategorySubcategories(Long parentCategoryId, Collection<Category> subcategories) throws EcommerceBusinessLogicException, IOException;
    Category updateCategoryName(Long id, Category category) throws EcommerceBusinessLogicException;
    Category deleteCategory(Long id) throws EcommerceBusinessLogicException;
}

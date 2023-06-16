package com.ecommerce.cc.core.ports.in.product;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.category.Category;
import java.util.Collection;

public interface IQueryProduct {
    Collection<Category> readProductCategories(String id) throws EcommerceBusinessLogicException;
}

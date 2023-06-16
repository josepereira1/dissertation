package com.ecommerce.qct.core.ports.in.tree;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;

public interface IQueryCategoryTree {
    CategoryTree readCategoryTree() throws EcommerceBusinessLogicException;
}

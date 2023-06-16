package com.ecommerce.qcvp.core.ports.in.category;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;

public interface IQueryCategory {
    Category readCategoryProducts(Long id, int page, int productsPerPage) throws EcommerceBusinessLogicException;
}

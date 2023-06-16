package com.ecommerce.qcap.core.ports.in.category;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.business.resources.category.category.Visibility;

public interface IQueryCategory {
    Category readCategoryProductsByPaginationAndVisibility(Long id, int page, int productsPerPage, Visibility visibility) throws EcommerceBusinessLogicException;
    Category readVisibleCategoryProductsByPagination(Long id, int page, int productsPerPage) throws EcommerceBusinessLogicException;
}

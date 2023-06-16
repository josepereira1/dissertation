package com.ecommerce.qcap.core.business.logic.category.category.query;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.logic.category.category.query.features.ReadCategoryProductsByPaginationAndVisibility;
import com.ecommerce.qcap.core.business.logic.category.category.query.features.ReadVisibleCategoryProductsByPagination;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.business.resources.category.category.Visibility;
import com.ecommerce.qcap.core.ports.in.category.IQueryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryCategory implements IQueryCategory {

    @Autowired
    private ReadCategoryProductsByPaginationAndVisibility readCategoryProductsByPaginationAndVisibility;

    @Autowired
    private ReadVisibleCategoryProductsByPagination readVisibleCategoryProductsByPagination;

    @Override
    public Category readCategoryProductsByPaginationAndVisibility(Long id, int page, int productsPerPage, Visibility visibility) throws EcommerceBusinessLogicException {
        return readCategoryProductsByPaginationAndVisibility.readCategoryProducts(id, page, productsPerPage, visibility);
    }

    @Override
    public Category readVisibleCategoryProductsByPagination(Long id, int page, int productsPerPage) throws EcommerceBusinessLogicException {
        return readVisibleCategoryProductsByPagination.readVisibleCategoryProductsByPagination(id, page, productsPerPage);
    }
}

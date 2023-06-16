package com.ecommerce.qcvp.core.business.logic.category.category.query;

import com.ecommerce.qcvp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcvp.core.business.logic.category.category.query.features.ReadCategoryProducts;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.business.resources.category.category.Visibility;
import com.ecommerce.qcvp.core.ports.in.category.IQueryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryCategory implements IQueryCategory {

    @Autowired
    private ReadCategoryProducts readCategoryProducts;

    @Override
    public Category readCategoryProducts(Long id, int page, int productsPerPage) throws EcommerceBusinessLogicException {
        return readCategoryProducts.readCategoryProducts(id, page, productsPerPage);
    }
}

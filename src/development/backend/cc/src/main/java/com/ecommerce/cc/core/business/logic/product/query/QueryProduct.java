package com.ecommerce.cc.core.business.logic.product.query;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.logic.product.query.features.ReadProductCategories;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.in.product.IQueryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class QueryProduct implements IQueryProduct {

    @Autowired
    private ReadProductCategories readProductCategories;

    @Override
    public Collection<Category> readProductCategories(String id) throws EcommerceBusinessLogicException {
        return readProductCategories.readProductCategories(id);
    }
}

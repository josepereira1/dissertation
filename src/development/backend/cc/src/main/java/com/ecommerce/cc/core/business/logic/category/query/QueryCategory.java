package com.ecommerce.cc.core.business.logic.category.query;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.logic.category.query.features.ReadRootCategory;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.in.category.IQueryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryCategory implements IQueryCategory {

    @Autowired
    private ReadRootCategory readRootCategory;

    @Override
    public Category getRootCategory() throws EcommerceBusinessLogicException {
        return readRootCategory.readRootCategory();
    }
}

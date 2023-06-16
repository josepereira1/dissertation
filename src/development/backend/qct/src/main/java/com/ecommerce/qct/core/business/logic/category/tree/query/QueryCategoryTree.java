package com.ecommerce.qct.core.business.logic.category.tree.query;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.logic.category.tree.query.features.ReadCategoryTree;
import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import com.ecommerce.qct.core.ports.in.tree.IQueryCategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryCategoryTree implements IQueryCategoryTree {

    @Autowired
    private ReadCategoryTree readCategoryTree;

    @Override
    public CategoryTree readCategoryTree() throws EcommerceBusinessLogicException {
        return readCategoryTree.readCategoryTree();
    }
}

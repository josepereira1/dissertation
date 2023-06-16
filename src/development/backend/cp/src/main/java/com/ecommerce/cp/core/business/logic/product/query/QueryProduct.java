package com.ecommerce.cp.core.business.logic.product.query;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.logic.product.query.features.ReadProduct;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.in.product.IQueryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryProduct implements IQueryProduct {

    @Autowired
    private ReadProduct readProduct;

    @Override
    public Product readProduct(String id) throws EcommerceBusinessLogicException {
        return readProduct.readProduct(id);
    }
}

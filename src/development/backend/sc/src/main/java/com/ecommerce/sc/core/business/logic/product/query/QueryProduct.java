package com.ecommerce.sc.core.business.logic.product.query;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.logic.product.query.features.ReadProduct;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.ports.in.product.IQueryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryProduct implements IQueryProduct {

    @Autowired
    private ReadProduct readProduct;

    @Override
    public Product readProduct(String productId) throws EcommerceBusinessLogicException {
        return readProduct.readProduct(productId);
    }
}

package com.ecommerce.qp.core.business.logic.product.query;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.logic.product.query.features.ReadProduct;
import com.ecommerce.qp.core.business.logic.product.query.features.ReadProductsByIds;
import com.ecommerce.qp.core.business.logic.product.query.features.ReadVisibleAndNotVisibleProduct;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.in.product.IQueryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class QueryProduct implements IQueryProduct {

    @Autowired
    private ReadProduct readProduct;

    @Autowired
    private ReadProductsByIds readProductsByIds;

    @Autowired
    private ReadVisibleAndNotVisibleProduct readVisibleAndNotVisibleProduct;

    @Override
    public Product readProduct(String id) throws EcommerceBusinessLogicException {
        return readProduct.readProduct(id);
    }

    @Override
    public Product readVisibleAndNotVisibleProduct(String id) throws EcommerceBusinessLogicException {
        return readVisibleAndNotVisibleProduct.readVisibleAndNotVisibleProduct(id);
    }

    @Override
    public Collection<Product> readProductsByIds(List<String> ids) throws EcommerceBusinessLogicException {
        return readProductsByIds.getProductsById(ids);
    }
}

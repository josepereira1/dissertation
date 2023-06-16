package com.ecommerce.inventory.core.business.logic.product.query;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.business.logic.product.query.features.ReadProduct;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.resources.StockStatus;
import com.ecommerce.inventory.core.ports.in.product.IQueryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class QueryProduct implements IQueryProduct {

    @Autowired
    private ReadProduct readProduct;

    @Override
    public Product readProduct(String id) throws EcommerceBusinessLogicException {
        return readProduct.readProduct(id);
    }

    @Override
    public Collection<Product> readAllProducts() {
        return null;
    }

    @Override
    public Collection<Product> readAllProductsByStatus(StockStatus stockStatus) {
        return null;
    }
}

package com.ecommerce.inventory.core.ports.in.product;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.resources.StockStatus;
import java.util.Collection;

public interface IQueryProduct {
    Product readProduct(String id) throws EcommerceBusinessLogicException;
    Collection<Product> readAllProducts();
    Collection<Product> readAllProductsByStatus(StockStatus stockStatus);
}

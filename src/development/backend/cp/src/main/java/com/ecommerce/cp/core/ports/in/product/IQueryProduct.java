package com.ecommerce.cp.core.ports.in.product;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.resources.product.Product;

public interface IQueryProduct {
    Product readProduct(String id) throws EcommerceBusinessLogicException;
}

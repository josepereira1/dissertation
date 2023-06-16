package com.ecommerce.sc.core.ports.in.product;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.resources.Product;

public interface IQueryProduct {
    Product readProduct(String productId) throws EcommerceBusinessLogicException;
}

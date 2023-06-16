package com.ecommerce.qp.core.ports.in.product;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.resources.product.Product;

public interface ICommandProduct {
    Product create(Product product) throws EcommerceBusinessLogicException;
    Product update(String id, Product product) throws EcommerceBusinessLogicException;
    void delete(String id) throws EcommerceBusinessLogicException;
}

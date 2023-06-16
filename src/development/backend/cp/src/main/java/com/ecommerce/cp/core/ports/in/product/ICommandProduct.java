package com.ecommerce.cp.core.ports.in.product;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.resources.product.Product;

public interface ICommandProduct {
    Product createProduct(Product product) throws EcommerceBusinessLogicException;
    Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException;
    void deleteProduct(String id) throws EcommerceBusinessLogicException;
}

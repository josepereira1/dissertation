package com.ecommerce.sc.core.ports.in.product;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.resources.Product;

public interface ICommandProduct {
    Product createProduct(Product product) throws EcommerceBusinessLogicException;
    Product updateProduct(String productId, Product product) throws EcommerceBusinessLogicException;
    void deleteProduct(String productId) throws EcommerceBusinessLogicException;
}

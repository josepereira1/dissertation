package com.ecommerce.cc.core.ports.in.product;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.product.Product;
import java.io.IOException;
import java.util.Set;

public interface ICommandProduct {
    Product createProduct(Product product, Set<Long> categoryIds) throws EcommerceBusinessLogicException, IOException;
    Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException;
    void deleteProduct(String id) throws EcommerceBusinessLogicException;
    Product addCategories(String id, Set<Long> categories) throws EcommerceBusinessLogicException;
    Product removeCategories(String id, Set<Long> categories) throws EcommerceBusinessLogicException;
}

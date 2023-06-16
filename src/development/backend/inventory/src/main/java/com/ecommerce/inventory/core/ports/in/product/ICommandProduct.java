package com.ecommerce.inventory.core.ports.in.product;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.business.resources.Product;
import java.util.Collection;
import java.util.List;

public interface ICommandProduct {
    Product createProduct(Product product) throws EcommerceBusinessLogicException;
    Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException;
    Collection<Product> addStock(List<Product> products) throws EcommerceBusinessLogicException;
    Collection<Product> removeStock(List<Product> products) throws EcommerceBusinessLogicException;
    void deleteProduct(String id) throws EcommerceBusinessLogicException;
}

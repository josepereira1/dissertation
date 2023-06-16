package com.ecommerce.cc.core.ports.out.repository.product;

import com.ecommerce.cc.core.business.resources.product.Product;

public interface ICommandProductRepository {
    Product save(Product product);
    Iterable<Product> saveAll(Iterable<Product> products);
    void deleteById(String id);
}

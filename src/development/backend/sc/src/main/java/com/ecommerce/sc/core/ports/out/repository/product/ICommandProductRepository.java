package com.ecommerce.sc.core.ports.out.repository.product;

import com.ecommerce.sc.core.business.resources.Product;

public interface ICommandProductRepository {
    Product save(Product product);
    void deleteById(String id);
}

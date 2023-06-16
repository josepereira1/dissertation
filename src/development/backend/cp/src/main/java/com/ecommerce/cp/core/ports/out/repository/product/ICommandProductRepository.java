package com.ecommerce.cp.core.ports.out.repository.product;

import com.ecommerce.cp.core.business.resources.product.Product;

public interface ICommandProductRepository {
    Product save(Product product);
    void deleteById(String id);
}

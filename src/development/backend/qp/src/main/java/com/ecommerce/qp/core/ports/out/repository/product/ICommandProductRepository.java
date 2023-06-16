package com.ecommerce.qp.core.ports.out.repository.product;

import com.ecommerce.qp.core.business.resources.product.Product;

public interface ICommandProductRepository {
    Product save(Product product);
    void deleteById(String id);
}

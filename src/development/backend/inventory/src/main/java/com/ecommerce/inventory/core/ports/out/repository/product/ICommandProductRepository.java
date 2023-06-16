package com.ecommerce.inventory.core.ports.out.repository.product;

import com.ecommerce.inventory.core.business.resources.Product;
import java.util.List;

public interface ICommandProductRepository {
    Product save(Product product);
    List<Product> saveAll(Iterable<Product> products);
    void deleteById(String id);
}

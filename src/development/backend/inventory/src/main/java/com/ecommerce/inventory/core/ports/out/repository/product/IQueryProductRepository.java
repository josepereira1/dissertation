package com.ecommerce.inventory.core.ports.out.repository.product;

import com.ecommerce.inventory.core.business.resources.Product;
import java.util.Collection;
import java.util.Optional;

public interface IQueryProductRepository {
    Optional<Product> findById(String id);
    Optional<Product> findOneForUpdate(String id);
    boolean existsByIdOrSkuOrEan(String id, String sku, String ean);
}

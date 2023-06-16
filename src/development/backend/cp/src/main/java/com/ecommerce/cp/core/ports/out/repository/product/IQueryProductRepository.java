package com.ecommerce.cp.core.ports.out.repository.product;

import com.ecommerce.cp.core.business.resources.product.Product;
import java.util.Optional;

public interface IQueryProductRepository {
    Optional<Product> findById(String id);
    Optional<Product> findOneForRead(String id);
    Optional<Product> findOneForUpdate(String id);
    boolean existsProductByIdOrSkuOrEan(String id, String sku, String ean);
    boolean existsById(String id);
}

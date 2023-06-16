package com.ecommerce.inventory.outbound.repository.postgresql.product;

import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class QueryProductRepository implements IQueryProductRepository {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Optional<Product> findById(String id) {
        return productDAO.findById(id);
    }

    @Override
    public Optional<Product> findOneForUpdate(String id) {
        return productDAO.findOneForUpdate(id);
    }

    @Override
    public boolean existsByIdOrSkuOrEan(String id, String sku, String ean) {
        return productDAO.existsProductByIdOrSkuOrEan(id, sku, ean);
    }
}

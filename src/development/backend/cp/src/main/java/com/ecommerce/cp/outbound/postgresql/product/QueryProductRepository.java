package com.ecommerce.cp.outbound.postgresql.product;

import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Optional<Product> findOneForRead(String id) {
        return productDAO.findOneForRead(id);
    }

    @Override
    public Optional<Product> findOneForUpdate(String id) {
        return productDAO.findOneForUpdate(id);
    }

    @Override
    public boolean existsProductByIdOrSkuOrEan(String id, String sku, String ean) {
        return productDAO.existsProductByIdOrSkuOrEan(id, sku, ean).isPresent() ? true : false;
    }

    @Override
    public boolean existsById(String id) {
        return productDAO.existsById(id);
    }
}

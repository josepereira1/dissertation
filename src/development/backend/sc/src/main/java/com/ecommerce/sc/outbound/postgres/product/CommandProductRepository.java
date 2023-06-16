package com.ecommerce.sc.outbound.postgres.product;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.ports.out.repository.product.ICommandProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandProductRepository implements ICommandProductRepository {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public void deleteById(String id) {
        productDAO.deleteById(id);
    }
}

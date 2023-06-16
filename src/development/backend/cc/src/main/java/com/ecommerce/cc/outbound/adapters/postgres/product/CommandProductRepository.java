package com.ecommerce.cc.outbound.adapters.postgres.product;

import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.repository.product.ICommandProductRepository;
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
    public Iterable<Product> saveAll(Iterable<Product> products) {
        return productDAO.saveAll(products);
    }

    @Override
    public void deleteById(String id){
        productDAO.deleteById(id);
    }
}

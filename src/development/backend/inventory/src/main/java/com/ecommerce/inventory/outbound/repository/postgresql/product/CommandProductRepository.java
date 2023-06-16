package com.ecommerce.inventory.outbound.repository.postgresql.product;

import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.ports.out.repository.product.ICommandProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandProductRepository implements ICommandProductRepository {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public List<Product> saveAll(Iterable<Product> products){
        return productDAO.saveAll(products);
    }

    @Override
    public void deleteById(String id) {
        productDAO.deleteById(id);
    }
}

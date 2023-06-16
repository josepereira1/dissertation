package com.ecommerce.cp.outbound.postgresql.product;

import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.out.repository.product.ICommandProductRepository;
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
    public void deleteById(String id){
        productDAO.deleteById(id);
    }
}

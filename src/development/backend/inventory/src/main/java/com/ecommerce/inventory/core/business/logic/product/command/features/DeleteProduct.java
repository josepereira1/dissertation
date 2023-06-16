package com.ecommerce.inventory.core.business.logic.product.command.features;

import com.ecommerce.inventory.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.inventory.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class DeleteProduct {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(String id) throws ProductWithThatIdNotExistException {
        Optional<Product> optional = queryProductRepository.findOneForUpdate(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        Product product = optional.get();
        product.setDeleted(true);
        commandProductRepository.save(product);
    }
}

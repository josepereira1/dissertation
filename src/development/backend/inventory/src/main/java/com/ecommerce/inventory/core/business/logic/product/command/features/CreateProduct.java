package com.ecommerce.inventory.core.business.logic.product.command.features;

import com.ecommerce.inventory.core.business.exceptions.product.ProductAlreadyExistsException;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.inventory.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Transactional(rollbackFor = Exception.class)
    public Product createProduct(Product product) throws ProductAlreadyExistsException {
        if(queryProductRepository.existsByIdOrSkuOrEan(product.getId(), product.getSku(), product.getEan())) throw ProductAlreadyExistsException.builder().build();
        product.setDeleted(false);
        return commandProductRepository.save(product);
    }
}

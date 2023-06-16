package com.ecommerce.sc.core.business.logic.product.command.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithIdAlreadyExistsException;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.sc.core.ports.out.repository.product.IQueryProductRepository;
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
    public Product createProduct(Product product) throws ProductWithIdAlreadyExistsException {
        if(queryProductRepository.existsById(product.getId())) throw ProductWithIdAlreadyExistsException.builder().id(product.getId()).build();
        else return commandProductRepository.save(product);
    }
}

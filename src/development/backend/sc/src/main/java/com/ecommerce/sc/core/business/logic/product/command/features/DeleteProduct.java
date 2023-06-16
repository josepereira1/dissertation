package com.ecommerce.sc.core.business.logic.product.command.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithThatIdNotExistException;
import com.ecommerce.sc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.sc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(String productId) throws ProductWithThatIdNotExistException {
        if(!queryProductRepository.existsById(productId)) throw ProductWithThatIdNotExistException.builder().id(productId).build();
        commandProductRepository.deleteById(productId);
    }
}

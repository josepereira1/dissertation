package com.ecommerce.qp.core.business.logic.product.command.features;

import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.qp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoveProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ProductNotExistException {
        if(!queryProductRepository.existsById(id)) throw ProductNotExistException.builder().id(id).build();
        commandProductRepository.deleteById(id);
    }
}

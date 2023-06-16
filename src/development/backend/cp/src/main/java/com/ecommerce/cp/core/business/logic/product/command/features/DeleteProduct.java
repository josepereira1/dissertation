package com.ecommerce.cp.core.business.logic.product.command.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cp.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.out.messaging.cqrs.product.ICQRSDeleteProduct;
import com.ecommerce.cp.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeleteProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICQRSDeleteProduct cqrsDeleteProduct;

    @Autowired
    private CommandVersion commandVersion;

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(String id) throws EcommerceBusinessLogicException {
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        commandProductRepository.deleteById(id);
        Product product = optional.get();
        Version productVersion = commandVersion.generateVersion("p" + product.getId());
        cqrsDeleteProduct.synchronize(productVersion, product);
    }
}

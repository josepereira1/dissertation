package com.ecommerce.inventory.core.business.logic.product.command.features;

import com.ecommerce.inventory.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.resources.StockStatus;
import com.ecommerce.inventory.core.ports.out.messaging.cqrs.ICQRSUpdateStockStatus;
import com.ecommerce.inventory.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.inventory.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateProduct {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private ICQRSUpdateStockStatus cqrsUpdateStockStatus;

    @Transactional(rollbackFor = Exception.class)
    public Product updateProduct(String id, Product updates) throws ProductWithThatIdNotExistException {
        Optional<Product> optional = queryProductRepository.findOneForUpdate(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        Product product = optional.get();
        StockStatus before = product.getStockStatus();
        product = updateProduct(product, updates);
        StockStatus after = product.getStockStatus();
        if((before == null && after != null) || (before != null && after != null && !before.equals(after)))
            cqrsUpdateStockStatus.synchronize(id, product.getStockStatus());
        return commandProductRepository.save(product);
    }

    private Product updateProduct(Product currentProduct, Product productUpdated){
        if(productUpdated.getStockQuantity() != null) {
            currentProduct.setStockQuantity(productUpdated.getStockQuantity());
            currentProduct.setStockStatus(CommonCommandProduct.getStatus(currentProduct.getStockQuantity()));
        }
        if(productUpdated.getStockUnit() != null) currentProduct.setStockUnit(productUpdated.getStockUnit());
        if(productUpdated.getSku() != null) currentProduct.setSku(productUpdated.getSku());
        if(productUpdated.getEan() != null) currentProduct.setEan(productUpdated.getEan());
        if(productUpdated.getPn() != null) currentProduct.setPn(productUpdated.getPn());
        if(productUpdated.getOwner() != null) currentProduct.setOwner(productUpdated.getOwner());
        if(productUpdated.getCountermeasure() != null) currentProduct.setCountermeasure(productUpdated.getCountermeasure());
        return currentProduct;
    }
}

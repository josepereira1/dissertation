package com.ecommerce.inventory.core.business.logic.product.command.features;

import com.ecommerce.inventory.core.business.exceptions.product.NoStockAvailableException;
import com.ecommerce.inventory.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.resources.StockStatus;
import com.ecommerce.inventory.core.ports.out.messaging.cqrs.ICQRSUpdateStockStatus;
import com.ecommerce.inventory.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.inventory.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class StockManagement {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICQRSUpdateStockStatus cqrsUpdateStockStatus;

    @Transactional(rollbackFor = Exception.class)
    public Collection<Product> addStock(List<Product> products) throws ProductWithThatIdNotExistException, NoStockAvailableException{
        return setStockByMultiplier(products, 1);
    }

    @Transactional(rollbackFor = Exception.class)
    public Collection<Product> removeStock(List<Product> products) throws ProductWithThatIdNotExistException, NoStockAvailableException{
        return setStockByMultiplier(products, -1);
    }

    public Collection<Product> setStockByMultiplier(List<Product> products, Integer multiplier) throws ProductWithThatIdNotExistException, NoStockAvailableException {
        List<Product> productsUpdated = new ArrayList<>();
        List<Product> productsStockStatusChanged = new ArrayList<>();
        for(Product product : products) productsUpdated.add(addOrRemoveStockFromProducts(product.getId(), (multiplier*product.getStockQuantity()), productsStockStatusChanged));
        productsUpdated = commandProductRepository.saveAll(productsUpdated);
        //  TODO colocar o cqrs a enviar todos os produtos juntos
        for(Product product : productsStockStatusChanged) cqrsUpdateStockStatus.synchronize(product.getId(), product.getStockStatus());
        return productsUpdated;
    }

    private Product addOrRemoveStockFromProducts(String id, Double stockQuantity, List<Product> stockStatusChanged) throws ProductWithThatIdNotExistException, NoStockAvailableException {
        Optional<Product> optional = queryProductRepository.findOneForUpdate(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        Product product = optional.get();
        if(product.getStockQuantity() == null) throw NoStockAvailableException.builder().id(id).build();
        Double finalStockQuantity = product.getStockQuantity() + stockQuantity;
        if(finalStockQuantity < 0) throw NoStockAvailableException.builder().id(id).build();
        StockStatus before = product.getStockStatus();
        product.setStockQuantity(finalStockQuantity);
        product.setStockStatus(CommonCommandProduct.getStatus(product.getStockQuantity()));
        StockStatus after = product.getStockStatus();
        if((before == null && after != null) || (before != null && after != null && !before.equals(after)))
            stockStatusChanged.add(product);
        return product;
    }
}

package com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in;

import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.dtos.ProductDTO;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSagaProductBackup;

public interface ICreateOrderSagaOrderInMapper {
    static CreateOrderSagaProductBackup toCreateOrderSagaProductBackup(ProductDTO product){
        CreateOrderSagaProductBackup tmp1 = new CreateOrderSagaProductBackup();
        tmp1.setProductId(product.getId());
        tmp1.setQuantity(product.getQuantity());
        return tmp1;
    }

    static Product toProduct(ProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setStockQuantity(product.getQuantity());
        return tmp1;
    }

    static Product toProduct(CreateOrderSagaProductBackup product){
        Product tmp1 = new Product();
        tmp1.setId(product.getProductId());
        tmp1.setStockQuantity((-1) * product.getQuantity());
        return tmp1;
    }
}

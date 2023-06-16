package com.ecommerce.inventory.core.business.saga.sagas.updateproduct.mappers.in;


import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.saga.sagas.updateproduct.mappers.in.dtos.ProductDTO;
import com.ecommerce.inventory.core.business.saga.sagas.updateproduct.resources.UpdateProductSagaProductBackup;

public interface IUpdateProductSagaInMapper {
    static Product toProduct(ProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static Product toProduct(UpdateProductSagaProductBackup product){
        Product tmp1 = new Product();
        tmp1.setId(product.getProductId());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static UpdateProductSagaProductBackup toUpdateProductSagaProductBackup(Product product){
        UpdateProductSagaProductBackup tmp1 = new UpdateProductSagaProductBackup();
        tmp1.setProductId(product.getId());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}

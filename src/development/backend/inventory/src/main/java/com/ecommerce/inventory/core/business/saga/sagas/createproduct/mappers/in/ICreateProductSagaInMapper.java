package com.ecommerce.inventory.core.business.saga.sagas.createproduct.mappers.in;

import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.saga.sagas.createproduct.mappers.in.dtos.ProductDTO;

public interface ICreateProductSagaInMapper {
    static Product toProduct(ProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}

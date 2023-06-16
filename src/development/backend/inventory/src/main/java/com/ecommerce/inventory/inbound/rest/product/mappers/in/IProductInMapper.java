package com.ecommerce.inventory.inbound.rest.product.mappers.in;

import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.inbound.rest.product.mappers.in.dtos.UpdateProductFeatureProductDTO;

public interface IProductInMapper {

    static Product toProduct(UpdateProductFeatureProductDTO product){
        Product tmp1 = new Product();
        tmp1.setStockUnit(product.getStockUnit());
        tmp1.setStockQuantity(product.getStockQuantity());
        return tmp1;
    }
}

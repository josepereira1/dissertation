package com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.mappers.in;

import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.mappers.in.dtos.UpdateStockStatusDTO;

public interface IProductInMapper {
    static Product toProduct(UpdateStockStatusDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setStockStatus(product.getStockStatus());
        return tmp1;
    }
}

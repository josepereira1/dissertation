package com.ecommerce.qp.inbound.messaging.cqrs.product.delete.mappers.in;

import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.inbound.messaging.cqrs.product.delete.mappers.in.dtos.DeleteProductFeatureProductDTO;

public interface IProductInMapper {
    static Product toProduct(DeleteProductFeatureProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        return tmp1;
    }
}

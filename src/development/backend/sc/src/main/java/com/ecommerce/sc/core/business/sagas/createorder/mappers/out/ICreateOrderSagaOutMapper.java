package com.ecommerce.sc.core.business.sagas.createorder.mappers.out;

import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.business.sagas.createorder.mappers.out.dtos.ProductDTO;

public interface ICreateOrderSagaOutMapper {

    static ProductDTO toProductDTO(ShoppingCartAndProduct shoppingCartAndProduct){
        ProductDTO tmp1 = new ProductDTO();
        tmp1.setId(shoppingCartAndProduct.getId().getProductId());
        tmp1.setQuantity(shoppingCartAndProduct.getQuantity());
        return tmp1;
    }
}

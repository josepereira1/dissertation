package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in.dtos.UpdateShoppingCartDTO;

public interface IShoppingCartInMapper {

    static ShoppingCartAndProduct toShoppingCartAndProduct(UpdateShoppingCartDTO product){
        ShoppingCartAndProduct tmp1 = new ShoppingCartAndProduct();
        Product tmp2 = new Product();
        tmp1.setQuantity(product.getQuantity());
        tmp2.setId(product.getProductId());
        tmp1.setProduct(tmp2);
        return tmp1;
    }
}

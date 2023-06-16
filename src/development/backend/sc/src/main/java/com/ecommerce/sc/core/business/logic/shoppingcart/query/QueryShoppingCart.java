package com.ecommerce.sc.core.business.logic.shoppingcart.query;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.logic.shoppingcart.query.features.ReadShoppingCart;
import com.ecommerce.sc.core.business.logic.shoppingcart.query.features.ReadShoppingCartProducts;
import com.ecommerce.sc.core.business.logic.shoppingcart.query.features.ReadShoppingCartSize;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.ports.in.shoppingcart.IQueryShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class QueryShoppingCart implements IQueryShoppingCart {

    @Autowired
    private ReadShoppingCart readShoppingCart;

    @Autowired
    private ReadShoppingCartProducts readShoppingCartProducts;

    @Autowired
    private ReadShoppingCartSize readShoppingCartSize;

    @Override
    public ShoppingCart readShoppingCart(String id) throws EcommerceBusinessLogicException {
        return readShoppingCart.readShoppingCart(id);
    }

    @Override
    public Set<ShoppingCartAndProduct> readShoppingCartProducts(String shoppingCartId) throws EcommerceBusinessLogicException{
        return readShoppingCartProducts.readShoppingCartAndProducts(shoppingCartId);
    }

    @Override
    public Integer readShoppingCartSize(String id) throws EcommerceBusinessLogicException {
        return readShoppingCartSize.readShoppingCartSize(id);
    }
}

package com.ecommerce.sc.core.business.logic.shoppingcart.command;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.logic.shoppingcart.command.features.CreateShoppingCart;
import com.ecommerce.sc.core.business.logic.shoppingcart.command.features.DeleteShoppingCartProducts;
import com.ecommerce.sc.core.business.logic.shoppingcart.command.features.UpdateShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.ports.in.shoppingcart.ICommandShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class CommandShoppingCart implements ICommandShoppingCart {

    @Autowired
    private CreateShoppingCart createShoppingCart;

    @Autowired
    private UpdateShoppingCart updateShoppingCart;

    @Autowired
    private DeleteShoppingCartProducts deleteShoppingCartProducts;

    @Override
    public ShoppingCart createShoppingCart(String shoppingCartId) throws EcommerceBusinessLogicException{
        return createShoppingCart.createShoppingCart(shoppingCartId);
    }

    @Override
    public ShoppingCart updateShoppingCart(String shoppingCartId, Set<ShoppingCartAndProduct> products) throws EcommerceBusinessLogicException {
        return updateShoppingCart.updateShoppingCart(shoppingCartId, products);
    }

    @Override
    public ShoppingCart deleteShoppingCartProducts(String shoppingCartId) throws EcommerceBusinessLogicException {
        return deleteShoppingCartProducts.deleteShoppingCartProducts(shoppingCartId);
    }
}

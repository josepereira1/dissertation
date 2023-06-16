package com.ecommerce.sc.core.business.logic.shoppingcart.command.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartAlreadyExistsException;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.ICommandShoppingCartRepository;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.IQueryShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateShoppingCart {

    @Autowired
    private ICommandShoppingCartRepository commandShoppingCartRepository;

    @Autowired
    private IQueryShoppingCartRepository queryShoppingCartRepository;

    @Transactional(rollbackFor = Exception.class)
    public ShoppingCart createShoppingCart(String id) throws ShoppingCartAlreadyExistsException {
        if(queryShoppingCartRepository.existsById(id)) throw ShoppingCartAlreadyExistsException.builder().id(id).build();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(id);
        shoppingCart.setSize(0);
        shoppingCart.setTotal(0d);
        return commandShoppingCartRepository.save(shoppingCart);
    }
}

package com.ecommerce.sc.core.business.logic.shoppingcart.command.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartNotExistsException;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.ICommandShoppingCartRepository;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.IQueryShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.TreeSet;

@Service
public class DeleteShoppingCartProducts {

    @Autowired
    private ICommandShoppingCartRepository commandShoppingCartRepository;

    @Autowired
    private IQueryShoppingCartRepository queryShoppingCartRepository;

    @Transactional(rollbackFor = Exception.class)
    public ShoppingCart deleteShoppingCartProducts(String shoppingCartId) throws ShoppingCartNotExistsException {
        if(!queryShoppingCartRepository.existsById(shoppingCartId)) throw ShoppingCartNotExistsException.builder().id(shoppingCartId).build();
        commandShoppingCartRepository.deleteById(shoppingCartId);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartId);
        shoppingCart.setSize(0);
        shoppingCart.setTotal(0d);
        shoppingCart.setProducts(new TreeSet<>());
        return commandShoppingCartRepository.save(shoppingCart);
    }
}

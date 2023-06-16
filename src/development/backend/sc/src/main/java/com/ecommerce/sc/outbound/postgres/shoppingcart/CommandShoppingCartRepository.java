package com.ecommerce.sc.outbound.postgres.shoppingcart;

import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.ICommandShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandShoppingCartRepository implements ICommandShoppingCartRepository {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartDAO.save(shoppingCart);
    }

    @Override
    public void deleteById(String id) {
        shoppingCartDAO.deleteById(id);
    }
}

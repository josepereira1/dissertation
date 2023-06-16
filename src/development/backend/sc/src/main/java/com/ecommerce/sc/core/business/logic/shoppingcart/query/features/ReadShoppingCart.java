package com.ecommerce.sc.core.business.logic.shoppingcart.query.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartNotExistsException;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.IQueryShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ReadShoppingCart {

    @Autowired
    private IQueryShoppingCartRepository queryShoppingCartRepository;

    public ShoppingCart readShoppingCart(String id) throws ShoppingCartNotExistsException {
        Optional<ShoppingCart> optional = queryShoppingCartRepository.findOneForRead(id);
        if(!optional.isPresent()) throw ShoppingCartNotExistsException.builder().id(id).build();
        return optional.get();
    }
}

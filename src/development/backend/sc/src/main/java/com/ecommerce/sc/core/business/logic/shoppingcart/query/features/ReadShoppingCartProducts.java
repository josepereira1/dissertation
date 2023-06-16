package com.ecommerce.sc.core.business.logic.shoppingcart.query.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartNotExistsException;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.IQueryShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class ReadShoppingCartProducts {

    @Autowired
    private IQueryShoppingCartRepository queryShoppingCartRepository;

    public Set<ShoppingCartAndProduct> readShoppingCartAndProducts(String shoppingCartId) throws ShoppingCartNotExistsException {
        Optional<ShoppingCart> optional = queryShoppingCartRepository.findOneForRead(shoppingCartId);
        if(!optional.isPresent()) throw ShoppingCartNotExistsException.builder().id(shoppingCartId).build();
        return optional.get().getProducts();
    }
}

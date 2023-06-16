package com.ecommerce.sc.core.business.logic.shoppingcart.query.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartNotExistsException;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.IQueryShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadShoppingCartSize {

    @Autowired
    private IQueryShoppingCartRepository queryShoppingCartRepository;

    public Integer readShoppingCartSize(String id) throws ShoppingCartNotExistsException {
        Optional<Integer> optional = queryShoppingCartRepository.findShoppingCartSize(id);
        if(!optional.isPresent()) throw ShoppingCartNotExistsException.builder().id(id).build();
        return optional.get();
    }
}

package com.ecommerce.sc.outbound.postgres.shoppingcart;

import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.IQueryShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryShoppingCartRepository implements IQueryShoppingCartRepository {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Override
    public Optional<ShoppingCart> findOneForRead(String id) {
        return shoppingCartDAO.findOneForRead(id);
    }

    @Override
    public Optional<ShoppingCart> findOneForUpdate(String id) {
        return shoppingCartDAO.findOneForUpdate(id);
    }

    @Override
    public Optional<Integer> findShoppingCartSize(String id) {
        return shoppingCartDAO.findShoppingCartSize(id);
    }

    @Override
    public boolean existsById(String id) {
        return shoppingCartDAO.existsById(id);
    }
}

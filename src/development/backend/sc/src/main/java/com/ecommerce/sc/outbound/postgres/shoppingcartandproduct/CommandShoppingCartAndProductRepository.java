package com.ecommerce.sc.outbound.postgres.shoppingcartandproduct;

import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.ports.out.repository.shoppingcartandproduct.ICommandShoppingCartAndProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandShoppingCartAndProductRepository implements ICommandShoppingCartAndProductRepository {

    @Autowired
    private ShoppingCartAndProductDAO shoppingCartAndProductDAO;

    @Override
    public Iterable<ShoppingCartAndProduct> saveAll(Iterable<ShoppingCartAndProduct> shoppingCartAndProducts) {
        return shoppingCartAndProductDAO.saveAll(shoppingCartAndProducts);
    }

    @Override
    public void deleteAll(Iterable<ShoppingCartAndProduct> shoppingCartAndProducts) {
        shoppingCartAndProductDAO.deleteAll(shoppingCartAndProducts);
    }
}

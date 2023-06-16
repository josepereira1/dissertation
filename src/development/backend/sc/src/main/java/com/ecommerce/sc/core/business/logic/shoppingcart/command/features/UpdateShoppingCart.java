package com.ecommerce.sc.core.business.logic.shoppingcart.command.features;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithThatIdNotExistException;
import com.ecommerce.sc.core.business.logic.shoppingcart.command.CommandShoppingCart;
import com.ecommerce.sc.core.business.resources.*;
import com.ecommerce.sc.core.ports.out.repository.product.IQueryProductRepository;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.ICommandShoppingCartRepository;
import com.ecommerce.sc.core.ports.out.repository.shoppingcart.IQueryShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UpdateShoppingCart {

    @Autowired
    private ICommandShoppingCartRepository commandShoppingCartRepository;

    @Autowired
    private IQueryShoppingCartRepository queryShoppingCartRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private CommandShoppingCart commandShoppingCart;

    @Transactional(rollbackFor = Exception.class)
    public ShoppingCart updateShoppingCart(String id, Set<ShoppingCartAndProduct> products) throws EcommerceBusinessLogicException {
        ShoppingCart shoppingCart;
        Optional<ShoppingCart> optional1 = queryShoppingCartRepository.findOneForUpdate(id);
        if(!optional1.isPresent()) shoppingCart = commandShoppingCart.createShoppingCart(id);
        else shoppingCart = optional1.get();
        addOrUpdateProductsToCart(shoppingCart, products);
        updateShoppingCartTotalAndSize(shoppingCart);
        return commandShoppingCartRepository.save(shoppingCart);
    }

    private void addOrUpdateProductsToCart(ShoppingCart shoppingCart, Set<ShoppingCartAndProduct> products) throws ProductWithThatIdNotExistException {
        for(ShoppingCartAndProduct productAndQuantity : products){
            Optional<Product> optional2 = queryProductRepository.findOneForUpdate(productAndQuantity.getProduct().getId());
            if(!optional2.isPresent()) throw  ProductWithThatIdNotExistException.builder().id(productAndQuantity.getProduct().getId()).build();
            ShoppingCartAndProductId shoppingCartAndProductId = new ShoppingCartAndProductId();
            shoppingCartAndProductId.setShoppingCartId(shoppingCart.getId());
            shoppingCartAndProductId.setProductId(productAndQuantity.getProduct().getId());
            productAndQuantity.setId(shoppingCartAndProductId);
            productAndQuantity.setShoppingCart(shoppingCart);
            if(shoppingCart.getProducts() != null && shoppingCart.getProducts().contains(productAndQuantity)){
                for(ShoppingCartAndProduct product : shoppingCart.getProducts())
                    if(productAndQuantity.equals(product))
                        product.setQuantity(productAndQuantity.getQuantity());
            }else{
                productAndQuantity.setProduct(optional2.get());
                if(shoppingCart.getProducts() == null) shoppingCart.setProducts(new TreeSet<>());
                shoppingCart.getProducts().add(productAndQuantity);
            }
        }
    }

    private void updateShoppingCartTotalAndSize(ShoppingCart shoppingCart){
        Double shoppingCartTotalPrice = 0d;
        shoppingCart.setSize(shoppingCart.getProducts().size());
        for(ShoppingCartAndProduct product : shoppingCart.getProducts())
            shoppingCartTotalPrice = shoppingCartTotalPrice + (product.getProduct().getFinalPrice() * product.getQuantity());
        shoppingCart.setTotal(shoppingCartTotalPrice);
    }
}

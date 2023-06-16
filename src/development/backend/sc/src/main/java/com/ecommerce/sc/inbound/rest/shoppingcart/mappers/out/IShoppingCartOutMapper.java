package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.dtos.CreateShoppingCartDTO;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.dtos.ProductDTO;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.dtos.ShoppingCartAndProductDTO;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.dtos.ShoppingCartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;

public interface IShoppingCartOutMapper {

    static CreateShoppingCartDTO toCreateShoppingCartDTO(ShoppingCart shoppingCart){
        CreateShoppingCartDTO tmp1 = new CreateShoppingCartDTO();
        tmp1.setId(shoppingCart.getId());
        tmp1.setSize(shoppingCart.getSize());
        tmp1.setTotal(shoppingCart.getTotal());
        return tmp1;
    }

    static ShoppingCartDTO toShoppingCartDTO(ShoppingCart shoppingCart){
        ShoppingCartDTO tmp1 = new ShoppingCartDTO();
        tmp1.setId(shoppingCart.getId());
        tmp1.setSize(shoppingCart.getSize());
        tmp1.setTotal(shoppingCart.getTotal());
        if(shoppingCart.getProducts() != null)tmp1.setProducts(shoppingCart.getProducts().stream().map(IShoppingCartOutMapper::toShoppingCartAndProductDTO).collect(Collectors.toSet()));
        return tmp1;
    }

    static ShoppingCartAndProductDTO toShoppingCartAndProductDTO(ShoppingCartAndProduct shoppingCartAndProduct){
        ShoppingCartAndProductDTO tmp1 = new ShoppingCartAndProductDTO();
        tmp1.setQuantity(shoppingCartAndProduct.getQuantity());
        tmp1.setProduct(toProductDTO(shoppingCartAndProduct.getProduct()));
        return tmp1;
    }

    static ProductDTO toProductDTO(Product product){
        ProductDTO tmp1 = new ProductDTO();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setCurrency(product.getCurrency());
        tmp1.setFinalPrice(product.getFinalPrice());
        try {
            tmp1.setLinks(new ObjectMapper().readValue(product.getLinks(), Object.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}

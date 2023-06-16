package com.ecommerce.sc.inbound.rest.shoppingcart.command;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.sc.core.business.security.Role;
import com.ecommerce.sc.core.ports.in.shoppingcart.ICommandShoppingCart;
import com.ecommerce.sc.core.business.security.Authentication;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in.IShoppingCartInMapper;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in.dtos.UpdateShoppingCartDTO;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.IShoppingCartOutMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CommandShoppingCartController implements ICommandShoppingCartController{

    @Autowired
    private ICommandShoppingCart commandShoppingCart;

    @Autowired
    private Authentication authentication;

    @CrossOrigin
    @Override
    public ResponseEntity createAndUpdateShoppingCart(String token, String shoppingCartId, Set<UpdateShoppingCartDTO> products) throws EcommerceBusinessLogicException {
        Claims claims = authentication.authenticateAndGetClaims(token);
        if(claims.get("role").equals(Role.CONSUMER.name()) && !claims.getSubject().equals(shoppingCartId)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(IShoppingCartOutMapper.toShoppingCartDTO(commandShoppingCart.updateShoppingCart(shoppingCartId, products.stream().map(IShoppingCartInMapper::toShoppingCartAndProduct).collect(Collectors.toSet()))));
    }

    @CrossOrigin
    @Override
    public ResponseEntity deleteShoppingCartProducts(String token, String shoppingCartId) throws EcommerceBusinessLogicException {
        Claims claims = authentication.authenticateAndGetClaims(token);
        if(claims.get("role").equals(Role.CONSUMER.name()) && !claims.getSubject().equals(shoppingCartId)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(IShoppingCartOutMapper.toShoppingCartDTO(commandShoppingCart.deleteShoppingCartProducts(shoppingCartId)));
    }
}

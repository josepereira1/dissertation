package com.ecommerce.sc.inbound.rest.shoppingcart.query;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.sc.core.ports.in.shoppingcart.IQueryShoppingCart;
import com.ecommerce.sc.core.business.security.Authentication;
import com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.IShoppingCartOutMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class QueryShoppingCartController implements IQueryShoppingCartController{

    @Autowired
    private IQueryShoppingCart queryShoppingCart;

    @Autowired
    private Authentication authentication;

    @CrossOrigin
    @Override
    public ResponseEntity readShoppingCart(String token, String shoppingCartId) throws EcommerceBusinessLogicException {
        Claims claims = authentication.authenticateAndGetClaims(token);
        if(!claims.getSubject().equals(shoppingCartId)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(IShoppingCartOutMapper.toShoppingCartDTO(queryShoppingCart.readShoppingCart(shoppingCartId)));
    }

    @Override
    public ResponseEntity readShoppingCartSize(@Valid @NotBlank String token, @Valid @NotBlank String shoppingCartId) throws EcommerceBusinessLogicException {
        Claims claims = authentication.authenticateAndGetClaims(token);
        if(!claims.getSubject().equals(shoppingCartId)) throw UnauthorizedException.builder().build();
        return ResponseEntity.ok(queryShoppingCart.readShoppingCartSize(shoppingCartId));
    }
}

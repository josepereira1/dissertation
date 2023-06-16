package com.ecommerce.co.inbound.rest.order.query;

import com.ecommerce.co.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.co.core.ports.in.order.IQueryOrder;
import com.ecommerce.co.inbound.rest.order.mappers.in.IOrderInMapper;
import com.ecommerce.co.inbound.rest.order.mappers.in.dtos.OrderFilterParameterDTO;
import com.ecommerce.co.inbound.rest.order.mappers.out.IOrderOutMapper;
import com.ecommerce.co.core.business.security.Authentication;
import com.ecommerce.co.core.business.security.Role;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class QueryOrderController implements IQueryOrderController{

    @Autowired
    private IQueryOrder queryOrder;

    @Autowired
    private Authentication authentication;

    @Override
    public ResponseEntity readOrder(String token, String orderId) throws Exception {
        Claims claims = authentication.authenticateAndGetClaims(token);
        if(Role.CONSUMER.equals(Role.valueOf(String.valueOf(claims.get("role")))))
            return ResponseEntity.ok(IOrderOutMapper.toOrderDTO(queryOrder.readOrder(true, claims.getSubject(), orderId)));
        else if (Role.MANAGER.equals(Role.valueOf(String.valueOf(claims.get("role")))) || Role.ADMIN.equals(Role.valueOf(String.valueOf(claims.get("role")))))
            return ResponseEntity.ok(IOrderOutMapper.toOrderDTO(queryOrder.readOrder(false, null, orderId)));
        else throw UnauthorizedException.builder().build();
    }

    @Override
    public ResponseEntity readOrdersByStatus(OrderFilterParameterDTO orderFilterParameter) {
        return ResponseEntity.ok(queryOrder.readOrdersByStatus(IOrderInMapper.toOrderStatus(orderFilterParameter)).stream().map(IOrderOutMapper::toOrderIdAndStatusAndValuesAndInitialDateDTO).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity readClientOrders(String token, String clientId) throws Exception {
        Claims claims = authentication.authenticateAndGetClaims(token);
        if(Role.CONSUMER.equals(Role.valueOf(String.valueOf(claims.get("role")))))
            return ResponseEntity.ok(queryOrder.readClientOrders(true, claims.getSubject(), clientId).stream().map(IOrderOutMapper::toOrderIdAndStatusAndValuesAndInitialDateDTO).collect(Collectors.toList()));
        else if (Role.MANAGER.equals(Role.valueOf(String.valueOf(claims.get("role")))) || Role.ADMIN.equals(Role.valueOf(String.valueOf(claims.get("role")))))
            return ResponseEntity.ok(queryOrder.readClientOrders(false, null, clientId).stream().map(IOrderOutMapper::toOrderIdAndStatusAndValuesAndInitialDateDTO).collect(Collectors.toList()));
        else throw UnauthorizedException.builder().build();
    }
}

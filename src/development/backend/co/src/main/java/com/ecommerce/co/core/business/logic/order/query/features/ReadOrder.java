package com.ecommerce.co.core.business.logic.order.query.features;

import com.ecommerce.co.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.co.core.business.exceptions.order.OrderNotExistsException;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.ports.out.repository.order.IQueryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ReadOrder {

    @Autowired
    private IQueryOrderRepository queryOrderRepository;

    public Order readOrder(boolean authorization, String tokenSubject, String orderId) throws OrderNotExistsException, UnauthorizedException {
        Optional<Order> optional = queryOrderRepository.findById(orderId);
        if(!optional.isPresent()) throw OrderNotExistsException.builder().orderId(orderId).build();
        Order order = optional.get();
        if(authorization && !order.getClientId().equals(tokenSubject)) throw UnauthorizedException.builder().build();
        return order;
    }
}

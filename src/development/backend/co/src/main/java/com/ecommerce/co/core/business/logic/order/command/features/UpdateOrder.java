package com.ecommerce.co.core.business.logic.order.command.features;

import com.ecommerce.co.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.co.core.business.exceptions.order.OrderNotExistsException;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.ports.out.repository.order.ICommandOrderRepository;
import com.ecommerce.co.core.ports.out.repository.order.IQueryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UpdateOrder {

    @Autowired
    private IQueryOrderRepository queryOrderRepository;

    @Autowired
    private ICommandOrderRepository commandOrderRepository;

    @Transactional(rollbackFor = Exception.class)
    public Order updateOrder(boolean authorization, String tokenSubject, String id, Order updatedOrder) throws OrderNotExistsException, UnauthorizedException {
        Optional<Order> optional = queryOrderRepository.findById(id);
        if(!optional.isPresent()) throw OrderNotExistsException.builder().orderId(id).build();
        Order currentOrder = optional.get();
        if(authorization && !updatedOrder.getClientId().equals(tokenSubject)) throw UnauthorizedException.builder().build();
        currentOrder = updateOrder(currentOrder, updatedOrder);
        currentOrder = commandOrderRepository.save(currentOrder);
        return currentOrder;
    }

    private Order updateOrder(Order currentOrder, Order updatedOrder){
        if(updatedOrder.getCountermeasure() != null) currentOrder.setCountermeasure(updatedOrder.getCountermeasure());
        if(updatedOrder.getStatus() != null) {
            currentOrder.setEndDate(LocalDateTime.now());
            currentOrder.setStatus(updatedOrder.getStatus());
        }
        if(updatedOrder.getStatusCode() != null) currentOrder.setStatusCode(updatedOrder.getStatusCode());
        if(updatedOrder.getStatusMessage() != null) currentOrder.setStatusMessage(updatedOrder.getStatusMessage());
        return currentOrder;
    }
}

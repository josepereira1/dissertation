package com.ecommerce.co.core.business.logic.order.command.features;

import com.ecommerce.co.core.business.exceptions.order.OrderNotExistsException;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.ports.out.repository.order.ICommandOrderRepository;
import com.ecommerce.co.core.ports.out.repository.order.IQueryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateOrderStatus {

    @Autowired
    private ICommandOrderRepository commandOrderRepository;

    @Autowired
    private IQueryOrderRepository queryOrderRepository;

    @Transactional(rollbackFor = Exception.class)
    public Order updateOrderStatus(String id, OrderStatus status) throws OrderNotExistsException {
        Optional<Order> optional = queryOrderRepository.findById(id);
        if(!optional.isPresent()) throw OrderNotExistsException.builder().orderId(id).build();
        Order order = optional.get();
        order.setStatus(status);
        order.setStatusCode(null);
        order.setStatusMessage(null);
        return commandOrderRepository.save(order);
    }
}

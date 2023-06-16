package com.ecommerce.co.core.business.logic.order.query.features;

import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.ports.out.repository.order.IQueryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ReadOrdersByStatus {

    @Autowired
    private IQueryOrderRepository queryOrderRepository;

    public Collection<Order> readOrdersByStatus(OrderStatus status){
        return status != null ? queryOrderRepository.findOrderByStatus(status) : queryOrderRepository.findAll();
    }
}

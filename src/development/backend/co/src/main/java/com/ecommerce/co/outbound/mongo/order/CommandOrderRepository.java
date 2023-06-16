package com.ecommerce.co.outbound.mongo.order;

import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.ports.out.repository.order.ICommandOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandOrderRepository implements ICommandOrderRepository {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Order save(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public void deleteById(String id) {
        orderDAO.deleteById(id);
    }
}

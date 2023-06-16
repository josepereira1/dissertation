package com.ecommerce.co.outbound.mongo.order;

import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.ports.out.repository.order.IQueryOrderRepository;
import com.ecommerce.co.outbound.mongo.order.mappers.out.IOrderOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueryOrderRepository implements IQueryOrderRepository {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Optional<Order> findById(String id) {
        return orderDAO.findById(id);
    }

    @Override
    public Collection<Order> findOrderByClientId(String clientId) {
        return orderDAO.findOrderByClientId(clientId).stream().map(IOrderOutMapper::toOrder).collect(Collectors.toList());
    }

    @Override
    public Collection<Order> findOrderByStatus(OrderStatus status) {
        return orderDAO.findOrderByStatus(status).stream().map(IOrderOutMapper::toOrder).collect(Collectors.toList());
    }

    @Override
    public Collection<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public boolean existsById(String id) {
        return orderDAO.existsById(id);
    }
}

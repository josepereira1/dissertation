package com.ecommerce.co.core.ports.out.repository.order;

import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;

import java.util.Collection;
import java.util.Optional;

public interface IQueryOrderRepository {
    Optional<Order> findById(String id);
    Collection<Order> findOrderByClientId(String clientId);
    Collection<Order> findOrderByStatus(OrderStatus status);
    Collection<Order> findAll();
    boolean existsById(String id);
}

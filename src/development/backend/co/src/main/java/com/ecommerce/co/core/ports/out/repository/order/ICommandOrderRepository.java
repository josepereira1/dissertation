package com.ecommerce.co.core.ports.out.repository.order;

import com.ecommerce.co.core.business.resources.Order;

public interface ICommandOrderRepository {
    Order save(Order order);
    void deleteById(String id);
}

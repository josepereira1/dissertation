package com.ecommerce.co.core.ports.in.order;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;

import java.util.Collection;

public interface IQueryOrder {
    Order readOrder(boolean authorization, String tokenSubject, String id) throws EcommerceBusinessLogicException;
    Collection<Order> readClientOrders(boolean authorization, String tokenSubject, String clientId) throws EcommerceBusinessLogicException;
    Collection<Order> readOrdersByStatus(OrderStatus status);
}

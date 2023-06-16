package com.ecommerce.co.core.ports.in.order;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;

public interface ICommandOrder {
    Order createOrder(Order order) throws EcommerceBusinessLogicException;
    Order updateOrder(boolean authorization, String tokenSubject, String id, Order order) throws EcommerceBusinessLogicException;
    Order updateOrderStatus(String id, OrderStatus status) throws EcommerceBusinessLogicException;
    void removeOrder(String id) throws EcommerceBusinessLogicException;
}

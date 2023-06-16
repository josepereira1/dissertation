package com.ecommerce.co.core.business.logic.order.command;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.logic.order.command.features.CreateOrder;
import com.ecommerce.co.core.business.logic.order.command.features.RemoveOrder;
import com.ecommerce.co.core.business.logic.order.command.features.UpdateOrder;
import com.ecommerce.co.core.business.logic.order.command.features.UpdateOrderStatus;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.ports.in.order.ICommandOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandOrder implements ICommandOrder {

    @Autowired
    private CreateOrder createOrder;

    @Autowired
    private UpdateOrder updateOrder;

    @Autowired
    private UpdateOrderStatus updateOrderStatus;
    
    @Autowired
    private RemoveOrder removeOrder;

    @Override
    public Order createOrder(Order order) throws EcommerceBusinessLogicException {
        return createOrder.createOrder(order);
    }

    @Override
    public Order updateOrder(boolean authorization, String tokenSubject, String id, Order order) throws EcommerceBusinessLogicException{
        return updateOrder.updateOrder(authorization, tokenSubject, id, order);
    }

    @Override
    public Order updateOrderStatus(String id, OrderStatus status) throws EcommerceBusinessLogicException {
        return updateOrderStatus.updateOrderStatus(id, status);
    }

    @Override
    public void removeOrder(String id) throws EcommerceBusinessLogicException {
        removeOrder.removeOrder(id);
    }
}

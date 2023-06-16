package com.ecommerce.co.core.business.logic.order.query;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.logic.order.query.features.ReadOrder;
import com.ecommerce.co.core.business.logic.order.query.features.ReadOrdersByClientId;
import com.ecommerce.co.core.business.logic.order.query.features.ReadOrdersByStatus;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.ports.in.order.IQueryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class QueryOrder implements IQueryOrder {

    @Autowired
    private ReadOrder readOrder;

    @Autowired
    private ReadOrdersByClientId readOrdersByClientId;

    @Autowired
    private ReadOrdersByStatus readOrdersByStatus;

    @Override
    public Order readOrder(boolean authorization, String tokenSubject, String id) throws EcommerceBusinessLogicException {
        return readOrder.readOrder(authorization, tokenSubject, id);
    }

    @Override
    public Collection<Order> readClientOrders(boolean authorization, String tokenSubject, String clientId) throws EcommerceBusinessLogicException {
        return readOrdersByClientId.readOrdersByClientId(authorization, tokenSubject, clientId);
    }

    @Override
    public Collection<Order> readOrdersByStatus(OrderStatus status) {
        return readOrdersByStatus.readOrdersByStatus(status);
    }
}

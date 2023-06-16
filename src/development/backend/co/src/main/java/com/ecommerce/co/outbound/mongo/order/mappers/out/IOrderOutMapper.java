package com.ecommerce.co.outbound.mongo.order.mappers.out;

import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.outbound.mongo.order.mappers.out.projections.OrderIdAndClientIdAndStatusAndTotalAndInitialDate;

public interface IOrderOutMapper {
    static Order toOrder(OrderIdAndClientIdAndStatusAndTotalAndInitialDate order){
        Order tmp1 = new Order();
        tmp1.setId(order.getId());
        tmp1.setClientId(order.getClientId());
        tmp1.setStatus(order.getStatus());
        tmp1.setCurrency(order.getCurrency());
        tmp1.setTotal(order.getTotal());
        tmp1.setInitialDate(order.getInitialDate());
        return tmp1;
    }
}

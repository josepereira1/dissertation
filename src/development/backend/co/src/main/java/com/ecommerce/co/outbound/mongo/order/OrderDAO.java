package com.ecommerce.co.outbound.mongo.order;

import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.outbound.mongo.order.mappers.out.projections.OrderIdAndClientIdAndStatusAndTotalAndInitialDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface OrderDAO extends MongoRepository<Order, String> {
    Collection<OrderIdAndClientIdAndStatusAndTotalAndInitialDate> findOrderByStatus(OrderStatus status);
    Collection<OrderIdAndClientIdAndStatusAndTotalAndInitialDate> findOrderByClientId(String clientId);
}

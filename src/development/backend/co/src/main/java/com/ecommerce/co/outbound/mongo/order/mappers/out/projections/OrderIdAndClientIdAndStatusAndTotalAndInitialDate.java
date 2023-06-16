package com.ecommerce.co.outbound.mongo.order.mappers.out.projections;

import com.ecommerce.co.core.business.resources.OrderStatus;

import java.time.LocalDateTime;
import java.util.Date;

public interface OrderIdAndClientIdAndStatusAndTotalAndInitialDate {
    String getId();
    String getClientId();
    OrderStatus getStatus();
    String getCurrency();
    Double getTotal();
    LocalDateTime getInitialDate();
}

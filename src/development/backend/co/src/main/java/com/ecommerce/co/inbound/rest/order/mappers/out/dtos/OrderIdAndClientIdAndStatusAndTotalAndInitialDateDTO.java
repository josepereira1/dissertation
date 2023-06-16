package com.ecommerce.co.inbound.rest.order.mappers.out.dtos;

import com.ecommerce.co.core.business.resources.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderIdAndClientIdAndStatusAndTotalAndInitialDateDTO {
    private String id;
    private String clientId;
    private OrderStatus status;
    private String currency;
    private Double total;
    private LocalDateTime initialDate;
}

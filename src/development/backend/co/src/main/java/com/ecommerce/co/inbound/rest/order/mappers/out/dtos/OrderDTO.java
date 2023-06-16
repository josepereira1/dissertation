package com.ecommerce.co.inbound.rest.order.mappers.out.dtos;

import com.ecommerce.co.core.business.resources.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {

    private String id;
    private String clientId;
    private String tin;

    private OrderStatus status;
    private Integer statusCode;
    private String statusMessage;

    private String currency;
    private Double subtotal;
    private Double discount;
    private Double shipping;
    private Double vat;
    private Double total;

    //  dates
    private LocalDateTime initialDate;
    private LocalDateTime endDate;

    private String trackingNumber;

    private List<ProductDTO> products;
    private AddressDTO chargeAddress;
    private AddressDTO deliveryAddress;
    private Object other;
}

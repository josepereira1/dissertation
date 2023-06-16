package com.ecommerce.co.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    private String id;

    //  client info
    private String clientId;
    private String tin;

    private OrderStatus status;
    private Integer statusCode;
    private String statusMessage;

    //  dates
    private LocalDateTime initialDate;
    private LocalDateTime endDate;

    private String currency;
    private Double subtotal;
    private Double discount;
    private Double shipping;
    private Double vat;
    private Double total;

    //  shipping
    //private String shipmentStatus;
    private String trackingNumber;

    //  free values
    private Object other;

    private List<Product> products;

    private Address deliveryAddress;
    private Address chargeAddress;

    private String owner;
    private CounterMeasure countermeasure;
}

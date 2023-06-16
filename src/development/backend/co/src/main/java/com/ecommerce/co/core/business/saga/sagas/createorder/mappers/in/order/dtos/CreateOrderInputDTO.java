package com.ecommerce.co.core.business.saga.sagas.createorder.mappers.in.order.dtos;

import com.ecommerce.co.core.business.resources.Address;
import com.ecommerce.co.core.business.resources.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderInputDTO {
    private String clientId;
    private String tin;
    private Object other;
    private List<Product> products;
    private Address chargeAddress;
    private Address deliveryAddress;
    private String token;
    private Integer errorCode;
    private String errorMessage;
}

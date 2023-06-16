package com.ecommerce.consumer.core.business.resources;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Consumer {

    @Id
    private String id;
    private String password;
    private String tin;
    private Contacts contacts;
    private Address deliveryAddress;
    private Address chargeAddress;
    private String token;

}

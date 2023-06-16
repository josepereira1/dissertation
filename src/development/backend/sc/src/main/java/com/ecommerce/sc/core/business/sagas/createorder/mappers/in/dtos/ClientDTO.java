package com.ecommerce.sc.core.business.sagas.createorder.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDTO {
    private String clientId;
    private String token;
}

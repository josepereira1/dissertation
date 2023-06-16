package com.ecommerce.manager.inbound.rest.authentication.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticateDTO {
    private String id;
    private String email;
}

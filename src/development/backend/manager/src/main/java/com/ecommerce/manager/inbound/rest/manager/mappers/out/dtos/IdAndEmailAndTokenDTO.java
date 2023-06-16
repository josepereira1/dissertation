package com.ecommerce.manager.inbound.rest.manager.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class IdAndEmailAndTokenDTO {
    private String id;
    private String email;
    private String token;
}

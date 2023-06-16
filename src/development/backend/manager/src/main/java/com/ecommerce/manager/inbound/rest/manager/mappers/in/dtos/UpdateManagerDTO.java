package com.ecommerce.manager.inbound.rest.manager.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateManagerDTO {

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;
}

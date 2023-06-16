package com.ecommerce.consumer.inbound.rest.authentication.mappers.out;

import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.inbound.rest.authentication.mappers.out.dtos.AuthenticationDTO;

public interface IAuthenticationOutMapper {

    static AuthenticationDTO toAuthenticateDTO(Consumer consumer){
        AuthenticationDTO tmp1 = new AuthenticationDTO();
        tmp1.setId(consumer.getId());
        return tmp1;
    }
}

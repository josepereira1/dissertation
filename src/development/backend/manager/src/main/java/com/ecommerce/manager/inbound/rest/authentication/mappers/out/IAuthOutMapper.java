package com.ecommerce.manager.inbound.rest.authentication.mappers.out;

import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.inbound.rest.authentication.mappers.out.dtos.AuthenticateDTO;

public interface IAuthOutMapper {

    static AuthenticateDTO toAuthenticateDTO(Manager manager){
        AuthenticateDTO tmp1 = new AuthenticateDTO();
        tmp1.setId(manager.getId());
        tmp1.setEmail(manager.getEmail());
        return tmp1;
    }
}

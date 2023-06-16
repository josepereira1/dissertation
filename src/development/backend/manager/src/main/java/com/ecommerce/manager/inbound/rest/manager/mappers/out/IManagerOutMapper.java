package com.ecommerce.manager.inbound.rest.manager.mappers.out;

import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.inbound.rest.manager.mappers.out.dtos.IdAndEmailAndTokenDTO;
import com.ecommerce.manager.inbound.rest.manager.mappers.out.dtos.IdAndEmailDTO;

public interface IManagerOutMapper {
    static IdAndEmailAndTokenDTO toIdAndEmailAndTokenDTO(Manager manager){
        IdAndEmailAndTokenDTO tmp1 = new IdAndEmailAndTokenDTO();
        tmp1.setId(manager.getId());
        tmp1.setEmail(manager.getEmail());
        tmp1.setToken(manager.getToken());
        return tmp1;
    }

    static IdAndEmailDTO toIdAndEmailDTO(Manager manager){
        IdAndEmailDTO tmp1 = new IdAndEmailDTO();
        tmp1.setId(manager.getId());
        tmp1.setEmail(manager.getEmail());
        return tmp1;
    }
}

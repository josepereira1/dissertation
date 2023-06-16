package com.ecommerce.manager.inbound.rest.manager.mappers.in;

import com.ecommerce.manager.core.business.resources.Manager;
import com.ecommerce.manager.inbound.rest.manager.mappers.in.dtos.CreateManagerDTO;
import com.ecommerce.manager.inbound.rest.manager.mappers.in.dtos.UpdateManagerDTO;

public interface IManagerInMapper {

    static Manager toEmployee(CreateManagerDTO employee){
        Manager tmp1 = new Manager();
        tmp1.setId(employee.getId());
        tmp1.setPassword(employee.getPassword());
        tmp1.setEmail(employee.getEmail());
        return tmp1;
    }

    static Manager toEmployee(UpdateManagerDTO employee){
        Manager tmp1 = new Manager();
        tmp1.setPassword(employee.getPassword());
        tmp1.setEmail(employee.getEmail());
        return tmp1;
    }
}

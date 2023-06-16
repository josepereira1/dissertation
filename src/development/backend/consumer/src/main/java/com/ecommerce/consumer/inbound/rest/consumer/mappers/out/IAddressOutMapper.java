package com.ecommerce.consumer.inbound.rest.consumer.mappers.out;

import com.ecommerce.consumer.core.business.resources.Address;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.out.dtos.AddressDTO;

public interface IAddressOutMapper {

    static AddressDTO toAddressDTO(Address address){
        AddressDTO tmp1 = new AddressDTO();
        tmp1.setAddress(address.getAddress());
        tmp1.setCity(address.getCity());
        tmp1.setPostalCode(address.getPostalCode());
        tmp1.setCountry(address.getCountry());
        return tmp1;
    }
}

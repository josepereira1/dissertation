package com.ecommerce.consumer.inbound.rest.consumer.mappers.in;

import com.ecommerce.consumer.core.business.resources.Address;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.CreateConsumerFeatureAddressDTO;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.UpdateConsumerFeatureAddressDTO;

public interface IAddressInMapper {

    static Address toAddress(CreateConsumerFeatureAddressDTO address){
        Address tmp1 = new Address();
        tmp1.setAddress(address.getAddress());
        tmp1.setCity(address.getCity());
        tmp1.setPostalCode(address.getPostalCode());
        tmp1.setCountry(address.getCountry());
        return tmp1;
    }

    static Address toAddress(UpdateConsumerFeatureAddressDTO address){
        Address tmp1 = new Address();
        tmp1.setAddress(address.getAddress());
        tmp1.setCity(address.getCity());
        tmp1.setPostalCode(address.getPostalCode());
        tmp1.setCountry(address.getCountry());
        return tmp1;
    }
}

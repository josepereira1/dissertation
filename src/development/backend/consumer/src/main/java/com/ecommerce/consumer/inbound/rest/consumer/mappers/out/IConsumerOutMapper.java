package com.ecommerce.consumer.inbound.rest.consumer.mappers.out;

import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.out.dtos.ConsumerDTO;

public interface IConsumerOutMapper {

    static ConsumerDTO toConsumerDTO(Consumer consumer){
        ConsumerDTO tmp1 = new ConsumerDTO();
        tmp1.setId(consumer.getId());
        if(consumer.getContacts() != null) tmp1.setContacts(IContactsOutMapper.toContactsDTO(consumer.getContacts()));
        if(consumer.getDeliveryAddress() != null) tmp1.setDeliveryAddress(IAddressOutMapper.toAddressDTO(consumer.getDeliveryAddress()));
        if(consumer.getChargeAddress() != null) tmp1.setChargeAddress(IAddressOutMapper.toAddressDTO(consumer.getChargeAddress()));
        tmp1.setToken(consumer.getToken());
        return tmp1;
    }
}

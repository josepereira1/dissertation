package com.ecommerce.consumer.inbound.rest.consumer.mappers.in;

import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.CreateConsumerFeatureClientDTO;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.UpdateConsumerFeatureClientDTO;

public interface IConsumerInMapper {

    static Consumer toConsumer(CreateConsumerFeatureClientDTO consumer){
        Consumer tmp1 = new Consumer();
        tmp1.setId(consumer.getId());
        tmp1.setPassword(consumer.getPassword());
        if(consumer.getContacts() != null) tmp1.setContacts(IContactsInMapper.toContacts(consumer.getContacts()));
        if(consumer.getDeliveryAddress() != null) tmp1.setDeliveryAddress(IAddressInMapper.toAddress(consumer.getDeliveryAddress()));
        if(consumer.getChargeAddress() != null) tmp1.setChargeAddress(IAddressInMapper.toAddress(consumer.getChargeAddress()));
        return tmp1;
    }

    static Consumer toConsumer(UpdateConsumerFeatureClientDTO consumer){
        Consumer tmp1 = new Consumer();
        tmp1.setPassword(consumer.getPassword());
        if(consumer.getContacts() != null) tmp1.setContacts(IContactsInMapper.toContacts(consumer.getContacts()));
        if(consumer.getDeliveryAddress() != null) tmp1.setDeliveryAddress(IAddressInMapper.toAddress(consumer.getDeliveryAddress()));
        if(consumer.getChargeAddress() != null) tmp1.setChargeAddress(IAddressInMapper.toAddress(consumer.getChargeAddress()));
        return tmp1;
    }
}

package com.ecommerce.consumer.inbound.rest.consumer.mappers.in;

import com.ecommerce.consumer.core.business.resources.Contacts;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.CreateConsumerFeatureContactsDTO;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos.UpdateConsumerFeatureContactsDTO;

public interface IContactsInMapper {

    static Contacts toContacts(CreateConsumerFeatureContactsDTO contacts){
        Contacts tmp1 = new Contacts();
        tmp1.setEmail(contacts.getEmail());
        tmp1.setMobileNumber(contacts.getMobileNumber());
        return tmp1;
    }

    static Contacts toContacts(UpdateConsumerFeatureContactsDTO contacts){
        Contacts tmp1 = new Contacts();
        tmp1.setEmail(contacts.getEmail());
        tmp1.setMobileNumber(contacts.getMobileNumber());
        return tmp1;
    }
}

package com.ecommerce.consumer.inbound.rest.consumer.mappers.out;

import com.ecommerce.consumer.core.business.resources.Contacts;
import com.ecommerce.consumer.inbound.rest.consumer.mappers.out.dtos.ContactsDTO;

public interface IContactsOutMapper {

    static ContactsDTO toContactsDTO(Contacts contacts){
        ContactsDTO tmp1 = new ContactsDTO();
        tmp1.setEmail(contacts.getEmail());
        tmp1.setMobileNumber(contacts.getMobileNumber());
        return tmp1;
    }
}

package com.ecommerce.consumer.core.business.logic.client.command.features;

import com.ecommerce.consumer.core.business.exceptions.consumer.ConsumerNotExistsException;
import com.ecommerce.consumer.core.business.resources.Address;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.business.resources.Contacts;
import com.ecommerce.consumer.core.ports.out.repository.consumer.ICommandConsumerRepository;
import com.ecommerce.consumer.core.ports.out.repository.consumer.IQueryConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateConsumer {

    @Autowired
    private CommonCommandConsumer commonCommandConsumer;

    @Autowired
    private ICommandConsumerRepository commandConsumerRepository;

    @Autowired
    private IQueryConsumerRepository queryConsumerRepository;

    @Transactional(rollbackFor = Exception.class)
    public Consumer updateConsumer(String id, Consumer consumer) throws ConsumerNotExistsException {
        Optional<Consumer> optional = queryConsumerRepository.findById(id);
        if(!optional.isPresent()) throw ConsumerNotExistsException.builder().id(id).build();
        Consumer currentConsumer = optional.get();
        currentConsumer = updateConsumer(consumer, currentConsumer);
        return commandConsumerRepository.save(currentConsumer);
    }

    private Consumer updateConsumer(Consumer updatedConsumer, Consumer currentConsumer){
        if(updatedConsumer.getPassword() != null) currentConsumer.setPassword(commonCommandConsumer.passwordEncoder().encode(updatedConsumer.getPassword()));
        if(updatedConsumer.getContacts() != null) currentConsumer.setContacts(updateContacts(updatedConsumer.getContacts(), currentConsumer.getContacts()));
        if(updatedConsumer.getDeliveryAddress() != null) currentConsumer.setDeliveryAddress(updateAddress(updatedConsumer.getDeliveryAddress(), currentConsumer.getDeliveryAddress()));
        if(updatedConsumer.getChargeAddress() != null) currentConsumer.setChargeAddress(updateAddress(updatedConsumer.getChargeAddress(), currentConsumer.getChargeAddress()));
        return currentConsumer;
    }

    private Contacts updateContacts(Contacts updatedContacts, Contacts currentContacts){
        if(updatedContacts.getEmail() != null) currentContacts.setEmail(updatedContacts.getEmail());
        if(updatedContacts.getMobileNumber() != null) currentContacts.setMobileNumber(updatedContacts.getMobileNumber());
        return currentContacts;
    }

    private Address updateAddress(Address updatedAddress, Address currentAddress){
        if(updatedAddress.getAddress() != null) currentAddress.setAddress(updatedAddress.getAddress());
        if(updatedAddress.getCity() != null) currentAddress.setCity(updatedAddress.getCity());
        if(updatedAddress.getPostalCode() != null) currentAddress.setPostalCode(updatedAddress.getPostalCode());
        if(updatedAddress.getCountry() != null) currentAddress.setCountry(updatedAddress.getCountry());
        return currentAddress;
    }
}

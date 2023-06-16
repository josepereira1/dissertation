package com.ecommerce.consumer.core.business.logic.client.command.features;

import com.ecommerce.consumer.core.business.exceptions.consumer.ConsumerAlreadyExistsException;
import com.ecommerce.consumer.core.business.logic.authentication.command.util.JwtProvider;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.business.resources.Role;
import com.ecommerce.consumer.core.ports.out.repository.consumer.ICommandConsumerRepository;
import com.ecommerce.consumer.core.ports.out.repository.consumer.IQueryConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class CreateConsumer {

    @Autowired
    private ICommandConsumerRepository commandConsumerRepository;

    @Autowired
    private IQueryConsumerRepository queryConsumerRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional(rollbackFor = Exception.class)
    public Consumer createConsumer(Consumer consumer) throws ConsumerAlreadyExistsException {
        //client.setId("c@" + client.getId() + "_" + generateRandomWords(1));   //  for testing
        consumer.setId("c@" + consumer.getId());
        if(queryConsumerRepository.existsById(consumer.getId())) throw ConsumerAlreadyExistsException.builder().id(consumer.getId()).build();
        consumer.setPassword(new BCryptPasswordEncoder().encode(consumer.getPassword()));
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", Role.CONSUMER);
        claims.put("email", consumer.getContacts().getEmail());
        consumer.setToken(jwtProvider.generateToken(new HashMap<>(), consumer.getId(), claims).replace("Bearer", ""));
        return commandConsumerRepository.save(consumer);
    }

    public String generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < numberOfWords ; i++)
            sb.append(randomStrings[i]).append(i != numberOfWords - 1 ? " " : "");


        return sb.toString();
    }
}

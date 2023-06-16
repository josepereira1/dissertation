package com.ecommerce.inventory.core.business.saga.sagas.createorder.features;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.business.logs.ILogs;
import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.inventory.core.business.saga.utils.CommonSaga;
import com.ecommerce.inventory.core.business.saga.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.inventory.core.business.saga.utils.exceptions.SagaNotExistsException;
import com.ecommerce.inventory.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.inventory.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import com.ecommerce.inventory.core.ports.out.repository.createordersaga.IQueryCreateOrderSagaRepository;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CreateOrderSagaCommit {

    @Value("${general.waiting.time.between.message.and.save}")
    private Long waitingTime;

    @Autowired
    private ICommandCreateOrderSagaRepository commandCreateOrderSagaRepository;

    @Autowired
    private IQueryCreateOrderSagaRepository queryCreateOrderSagaRepository;

    @Autowired
    private ILogs logs;

    public void callCommit(IMessage message){
        try {
            commit(message);
        } catch (Exception e) {
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            return;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void commit(IMessage message) throws EcommerceBusinessLogicException, InterruptedException {
        String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        Optional<CreateOrderSaga> optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("create order").sagaId(sagaId).build();
        }
        CreateOrderSaga createOrderSaga = optional.get();
        if(!createOrderSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("create order").sagaId(sagaId).build();
        createOrderSaga.setSagaStatus(SagaStatus.SUCCESS);
        commandCreateOrderSagaRepository.save(createOrderSaga);
    }
}

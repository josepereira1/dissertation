package com.ecommerce.sc.core.business.sagas.createorder.features;

import com.ecommerce.sc.core.business.logic.shoppingcart.command.CommandShoppingCart;
import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.sc.core.business.sagas.utils.CommonSaga;
import com.ecommerce.sc.core.business.sagas.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.sc.core.business.sagas.utils.exceptions.SagaNotExistsException;
import com.ecommerce.sc.core.business.sagas.utils.resources.SagaStatus;
import com.ecommerce.sc.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import com.ecommerce.sc.core.ports.out.repository.createordersaga.IQueryCreateOrderSagaRepository;
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
    private CommandShoppingCart commandShoppingCart;

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
    public void commit(IMessage message) throws Exception {
        String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        Optional<CreateOrderSaga> optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("create order").sagaId(sagaId).build();
        }
        CreateOrderSaga createOrderSaga = optional.get();
        if(!createOrderSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("create order").sagaId(sagaId).build();
        commandShoppingCart.deleteShoppingCartProducts(createOrderSaga.getShoppingCartId());
        createOrderSaga.setSagaStatus(SagaStatus.SUCCESS);
        commandCreateOrderSagaRepository.save(createOrderSaga);
    }
}

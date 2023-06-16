package com.ecommerce.co.core.business.saga.sagas.createorder.features;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.logic.order.command.CommandOrder;
import com.ecommerce.co.core.business.logs.ILogs;
import com.ecommerce.co.core.business.messaging.resources.IMessage;
import com.ecommerce.co.core.business.messaging.resources.MessageStatus;
import com.ecommerce.co.core.business.resources.CounterMeasure;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.business.saga.sagas.createorder.mappers.in.order.dtos.CreateOrderInputDTO;
import com.ecommerce.co.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.co.core.business.saga.utils.CommonSaga;
import com.ecommerce.co.core.business.saga.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.co.core.business.saga.utils.exceptions.SagaNotExistsException;
import com.ecommerce.co.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.co.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import com.ecommerce.co.core.ports.out.repository.createordersaga.IQueryCreateOrderSagaRepository;
import com.ecommerce.co.core.ports.out.saga.ISagaOrchestrator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CreateOrderSagaCompensatingTransaction {

    @Value("${general.waiting.time.between.message.and.save}")
    private Long waitingTime;

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private ICommandCreateOrderSagaRepository commandCreateOrderSagaRepository;

    @Autowired
    private IQueryCreateOrderSagaRepository queryCreateOrderSagaRepository;

    @Autowired
    private CommandOrder commandOrder;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    public void callCompensatingTransaction(IMessage message){
        try {
            compensatingTransaction(message);
        } catch (Exception e) {
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS,200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void compensatingTransaction(IMessage message) throws EcommerceBusinessLogicException, InterruptedException {
        CreateOrderInputDTO input;
        String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        Optional<CreateOrderSaga> optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("create order").sagaId(sagaId).build();
        }
        CreateOrderSaga createOrderSaga = optional.get();
        if(!createOrderSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("create order").sagaId(sagaId).build();
        input = gson.fromJson(message.getData(), CreateOrderInputDTO.class);

        System.err.println("\n\n\n\n\n\n\n\n\n" + input + "\n\n\n\n\n\n\n\n");

        Order order = new Order();
        order.setStatus(OrderStatus.CANCELED);
        order.setStatusCode(input.getErrorCode());
        order.setStatusMessage(input.getErrorMessage());
        order.setCountermeasure(CounterMeasure.UNLOCKED);
        commandOrder.updateOrder(false, null, createOrderSaga.getOrderId(), order);
        createOrderSaga.setSagaStatus(SagaStatus.ERROR);
        commandCreateOrderSagaRepository.save(createOrderSaga);
    }
}

package com.ecommerce.inventory.core.business.saga.sagas.createorder.features;

import com.ecommerce.inventory.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.inventory.core.business.logic.product.command.CommandProduct;
import com.ecommerce.inventory.core.business.logs.ILogs;
import com.ecommerce.inventory.core.business.messaging.resources.IMessage;
import com.ecommerce.inventory.core.business.messaging.resources.MessageStatus;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.ICreateOrderSagaOrderInMapper;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.resources.CreateOrderSaga;
import com.ecommerce.inventory.core.business.saga.utils.CommonSaga;
import com.ecommerce.inventory.core.business.saga.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.inventory.core.business.saga.utils.exceptions.SagaNotExistsException;
import com.ecommerce.inventory.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.inventory.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.inventory.core.ports.out.repository.createordersaga.ICommandCreateOrderSagaRepository;
import com.ecommerce.inventory.core.ports.out.repository.createordersaga.IQueryCreateOrderSagaRepository;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreateOrderSagaCompensatingTransaction {

    @Value("${general.waiting.time.between.message.and.save}")
    private Long waitingTime;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private ICommandCreateOrderSagaRepository commandCreateOrderSagaRepository;

    @Autowired
    private IQueryCreateOrderSagaRepository queryCreateOrderSagaRepository;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;

    public void callCompensatingTransaction(IMessage message){
        try{
            compensatingTransaction(message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            return;
        }
        sagaOrchestrator.replyToOrchestrator(message.getOwner(), MessageStatus.SUCCESS, 200, "", message.getMetadata(), message.getData());
    }

    @Transactional(rollbackFor = Exception.class)
    public void compensatingTransaction(IMessage message) throws EcommerceBusinessLogicException, InterruptedException {
        String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        Optional<CreateOrderSaga> optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryCreateOrderSagaRepository.findCreateOrderSagaBySagaId(sagaId);
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("create order").sagaId(sagaId).build();
        }
        CreateOrderSaga createOrderSaga = optional.get();
        List<Product> products = createOrderSaga.getProductsBackup().stream().map(ICreateOrderSagaOrderInMapper::toProduct).collect(Collectors.toList());
        commandProduct.addStock(products);
        if(!createOrderSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("create order").sagaId(sagaId).build();
        createOrderSaga.setSagaStatus(SagaStatus.ERROR);
        commandCreateOrderSagaRepository.save(createOrderSaga);
    }
}

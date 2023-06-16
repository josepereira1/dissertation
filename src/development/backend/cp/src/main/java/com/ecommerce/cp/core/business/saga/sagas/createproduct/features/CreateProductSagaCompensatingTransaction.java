package com.ecommerce.cp.core.business.saga.sagas.createproduct.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cp.core.business.logic.product.query.QueryProduct;
import com.ecommerce.cp.core.business.logs.ILogs;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.CommonSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.cp.core.business.saga.sagas.utils.exceptions.SagaNotExistsException;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaMetadata;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaStatus;
import com.ecommerce.cp.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.cp.core.ports.out.repository.createproductsaga.ICommandCreateProductSagaRepository;
import com.ecommerce.cp.core.ports.out.repository.createproductsaga.IQueryCreateProductSagaRepository;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CreateProductSagaCompensatingTransaction {

    @Value("${general.waiting.time.between.message.and.save}")
    private Long waitingTime;

    @Autowired
    private ICommandCreateProductSagaRepository commandCreateProductRepository;

    @Autowired
    private IQueryCreateProductSagaRepository queryCreateProductSagaRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private QueryProduct queryProduct;

    @Autowired
    private ISagaOrchestrator sagaOrchestrator;

    @Autowired
    private ILogs logs;


    public void callCompensatingTransaction(IMessage message){
        try {
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
        Optional<CreateProductSaga> optional = queryCreateProductSagaRepository.findCreateProductSagaBySagaId(sagaId);
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryCreateProductSagaRepository.findCreateProductSagaBySagaId(sagaId);
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("create product").sagaId(sagaId).build();
        }
        CreateProductSaga createProductSaga = optional.get();
        if(!createProductSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("create product").sagaId(sagaId).build();
        Product product = queryProduct.readProduct(createProductSaga.getProductId());
        commandProduct.deleteProduct(product.getId());
        createProductSaga.setSagaStatus(SagaStatus.ERROR);
        commandCreateProductRepository.save(createProductSaga);
    }
}

package com.ecommerce.cp.core.business.saga.sagas.updateproduct.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cp.core.business.logs.ILogs;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cp.core.business.resources.CounterMeasure;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.mappers.in.IUpdateProductSagaInMapper;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.CommonSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.cp.core.business.saga.sagas.utils.exceptions.SagaNotExistsException;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaMetadata;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaStatus;
import com.ecommerce.cp.core.ports.out.messaging.saga.ISagaOrchestrator;
import com.ecommerce.cp.core.ports.out.repository.updateproductsaga.ICommandUpdateProductSagaRepository;
import com.ecommerce.cp.core.ports.out.repository.updateproductsaga.IQueryUpdateProductSagaRepository;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateProductSagaCompensatingTransaction {

    @Value("${general.waiting.time.between.message.and.save}")
    private Long waitingTime;

    @Autowired
    private ICommandUpdateProductSagaRepository commandUpdateProductSagaRepository;

    @Autowired
    private IQueryUpdateProductSagaRepository queryUpdateProductSagaRepository;

    @Autowired
    private CommandProduct commandProduct;

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
        SagaMetadata sagaMetadata = CommonSaga.getSagaMetadata(message.getMetadata());
        Optional<UpdateProductSaga> optional = queryUpdateProductSagaRepository.findUpdateProductSagaBySagaId(sagaMetadata.getSagaId());
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryUpdateProductSagaRepository.findUpdateProductSagaBySagaId(sagaMetadata.getSagaId());
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("update product").sagaId(sagaMetadata.getSagaId()).build();
        }
        UpdateProductSaga updateProductSaga = optional.get();
        if(!updateProductSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("update product").sagaId(sagaMetadata.getSagaId()).build();
        Product product = IUpdateProductSagaInMapper.toProduct(updateProductSaga.getProductBackup());
        product.setCountermeasure(CounterMeasure.UNLOCKED);
        commandProduct.updateProduct(updateProductSaga.getProductId(), product);
        updateProductSaga.setSagaStatus(SagaStatus.ERROR);
        commandUpdateProductSagaRepository.save(updateProductSaga);
    }
}

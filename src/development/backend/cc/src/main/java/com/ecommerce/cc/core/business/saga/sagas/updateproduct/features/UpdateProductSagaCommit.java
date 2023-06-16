package com.ecommerce.cc.core.business.saga.sagas.updateproduct.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cc.core.business.logs.ILogs;
import com.ecommerce.cc.core.business.messaging.resources.IMessage;
import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cc.core.business.saga.utils.CommonSaga;
import com.ecommerce.cc.core.business.saga.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.cc.core.business.saga.utils.exceptions.SagaNotExistsException;
import com.ecommerce.cc.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.cc.core.ports.out.repository.saga.updateproduct.ICommandUpdateProductSagaRepository;
import com.ecommerce.cc.core.ports.out.repository.saga.updateproduct.IQueryUpdateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateProductSagaCommit {

    @Value("${general.waiting.time.between.message.and.save}")
    private Long waitingTime;

    @Autowired
    private ICommandUpdateProductSagaRepository commandUpdateProductSagaRepository;

    @Autowired
    private IQueryUpdateProductSagaRepository queryUpdateProductSagaRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private ILogs logs;

    public void callCommit(IMessage message){
        try{
            commit(message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            return;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void commit(IMessage message) throws EcommerceBusinessLogicException, InterruptedException {
        String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        Optional<UpdateProductSaga> optional = queryUpdateProductSagaRepository.findOneForUpdate(sagaId);
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryUpdateProductSagaRepository.findOneForUpdate(sagaId);
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("update product").sagaId(sagaId).build();
        }
        UpdateProductSaga updateProductSaga = optional.get();
        if(!updateProductSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("update product").sagaId(sagaId).build();
        Product product = new Product();
        product.setCountermeasure(CounterMeasure.UNLOCKED);
        updateProductSaga.setSagaStatus(SagaStatus.SUCCESS);
        commandUpdateProductSagaRepository.save(updateProductSaga);
        commandProduct.updateProduct(updateProductSaga.getProductId(),product);
    }
}

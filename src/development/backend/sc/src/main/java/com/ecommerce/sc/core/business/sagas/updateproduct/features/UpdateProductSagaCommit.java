package com.ecommerce.sc.core.business.sagas.updateproduct.features;

import com.ecommerce.sc.core.business.logic.product.command.CommandProduct;
import com.ecommerce.sc.core.business.logs.ILogs;
import com.ecommerce.sc.core.business.messaging.resources.IMessage;
import com.ecommerce.sc.core.business.resources.CounterMeasure;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.sc.core.business.sagas.utils.CommonSaga;
import com.ecommerce.sc.core.business.sagas.utils.exceptions.SagaNotExistsException;
import com.ecommerce.sc.core.business.sagas.utils.resources.SagaStatus;
import com.ecommerce.sc.core.ports.out.repository.updateproduct.ICommandUpdateProductSagaRepository;
import com.ecommerce.sc.core.ports.out.repository.updateproduct.IQueryUpdateProductSagaRepository;
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
    public void commit(IMessage message) throws Exception {
        String sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        Optional<UpdateProductSaga> optional = queryUpdateProductSagaRepository.findUpdateProductSagaBySagaId(sagaId);
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryUpdateProductSagaRepository.findUpdateProductSagaBySagaId(sagaId);
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("update product").sagaId(sagaId).build();
        }
        UpdateProductSaga updateProductSaga = optional.get();
        Product product = new Product();
        product.setCountermeasure(CounterMeasure.UNLOCKED);
        updateProductSaga.setSagaStatus(SagaStatus.SUCCESS);
        commandUpdateProductSagaRepository.save(updateProductSaga);
        commandProduct.updateProduct(updateProductSaga.getProductId(),product);
    }
}
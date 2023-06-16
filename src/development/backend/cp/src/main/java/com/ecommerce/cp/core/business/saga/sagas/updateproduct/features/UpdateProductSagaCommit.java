package com.ecommerce.cp.core.business.saga.sagas.updateproduct.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.logic.product.command.CommandProduct;
import com.ecommerce.cp.core.business.logs.ILogs;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.business.resources.CounterMeasure;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.CommonSaga;
import com.ecommerce.cp.core.business.saga.sagas.utils.exceptions.SagaIsInvalidException;
import com.ecommerce.cp.core.business.saga.sagas.utils.exceptions.SagaNotExistsException;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaMetadata;
import com.ecommerce.cp.core.business.saga.sagas.utils.resources.SagaStatus;
import com.ecommerce.cp.core.ports.out.repository.updateproductsaga.ICommandUpdateProductSagaRepository;
import com.ecommerce.cp.core.ports.out.repository.updateproductsaga.IQueryUpdateProductSagaRepository;
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
    private ICommandUpdateProductSagaRepository commandUpdateProductRepository;

    @Autowired
    private IQueryUpdateProductSagaRepository queryUpdateProductRepository;

    @Autowired
    private CommandProduct commandProduct;

    @Autowired
    private ILogs logs;

    public void callCommit(IMessage message){
        try {
            commit(message);
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + message);
            return;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void commit(IMessage message) throws EcommerceBusinessLogicException, InterruptedException {
        SagaMetadata sagaMetadata = CommonSaga.getSagaMetadata(message.getMetadata());
        Optional<UpdateProductSaga> optional = queryUpdateProductRepository.findUpdateProductSagaBySagaId(sagaMetadata.getSagaId());
        if(!optional.isPresent()){
            Thread.sleep(waitingTime); //  devido ao envio e receção da mensagem ser mais rápido do que o save da saga;
            optional = queryUpdateProductRepository.findUpdateProductSagaBySagaId(sagaMetadata.getSagaId());
            if(!optional.isPresent()) throw SagaNotExistsException.builder().name("update product").sagaId(sagaMetadata.getSagaId()).build();
        }
        UpdateProductSaga updateProductSaga = optional.get();
        if(!updateProductSaga.getSagaStatus().equals(SagaStatus.PENDING)) throw SagaIsInvalidException.builder().name("update product").sagaId(sagaMetadata.getSagaId()).build();
        Product product = new Product();
        product.setCountermeasure(CounterMeasure.UNLOCKED);
        commandProduct.updateProduct(updateProductSaga.getProductId(), product);
        updateProductSaga.setSagaStatus(SagaStatus.SUCCESS);
        commandUpdateProductRepository.save(updateProductSaga);
    }
}

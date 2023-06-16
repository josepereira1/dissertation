package com.ecommerce.cc.inbound.adapters.rest.tests;

import com.ecommerce.cc.core.business.saga.sagas.createproduct.features.CreateProductSagaCommit;
import com.ecommerce.cc.core.business.saga.sagas.createproduct.resources.CreateProductSaga;
import com.ecommerce.cc.core.business.saga.utils.resources.SagaStatus;
import com.ecommerce.cc.core.ports.out.repository.category.ICommandCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import com.ecommerce.cc.core.ports.out.repository.saga.createproduct.ICommandCreateProductSagaRepository;
import com.ecommerce.cc.core.ports.out.repository.saga.createproduct.IQueryCreateProductSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tests")
public class Tests {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCreateProductSagaRepository queryCreateProductSagaRepository;

    @Autowired
    private ICommandCreateProductSagaRepository commandCreateProductSagaRepository;

    /*@GetMapping(path = "/t1")
    @Transactional
    public ResponseEntity t1(){
        System.err.println("t1 started!");
        Optional<Category> optional = queryCategoryRepository.findOneForUpdate(3l);
        System.err.println("t1 get category!");
        Category category = optional.get();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("t1 acordou!");
        category.setName("t1 alterou");
        return ResponseEntity.ok(commandCategoryRepository.save(category));
    }

    @GetMapping(path = "/t2")
    @Transactional
    public ResponseEntity t2(){
        System.err.println("t2 started!");
        Optional<Category> optional = queryCategoryRepository.findOneForUpdate(3l);
        System.err.println("t2 get category!");
        Category category = optional.get();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("t2 acordou!");
        category.setName("t2 alterou");
        return ResponseEntity.ok(commandCategoryRepository.save(category));
    }*/

    @GetMapping(path = "/t1")
    @Transactional
    public ResponseEntity t1(){
        System.err.println("t1 started!");
        Optional<CreateProductSaga> optional = queryCreateProductSagaRepository.findCreateProductSagaBySagaId("610574bd0050e676554bcd22");
        System.err.println("t1 get category!");
        CreateProductSaga saga = optional.get();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("t1 acordou!");
        saga.setSagaStatus(SagaStatus.ERROR);
        return ResponseEntity.ok(commandCreateProductSagaRepository.save(saga));
    }

    @GetMapping(path = "/t2")
    @Transactional
    public ResponseEntity t2(){
        System.err.println("t2 started!");
        Optional<CreateProductSaga> optional = queryCreateProductSagaRepository.findCreateProductSagaBySagaId("610574bd0050e676554bcd22");
        System.err.println("t2 get category!");
        CreateProductSaga saga = optional.get();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("t2 acordou!");
        saga.setSagaStatus(SagaStatus.SUCCESS);
        return ResponseEntity.ok(commandCreateProductSagaRepository.save(saga));
    }

    @Autowired
    private CreateProductSagaCommit createProductSagaCommit;

    /*@GetMapping(path = "/t3")
    public ResponseEntity t3() throws EcommerceBusinessLogicException, InterruptedException {
        createProductSagaCommit.commit1();
        return null;
    }*/
}

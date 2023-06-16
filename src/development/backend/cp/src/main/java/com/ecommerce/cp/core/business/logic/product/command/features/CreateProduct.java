package com.ecommerce.cp.core.business.logic.product.command.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.exceptions.product.ProductAlreadyExistsException;
import com.ecommerce.cp.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.out.messaging.cqrs.product.ICQRSCreateProduct;
import com.ecommerce.cp.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;

@Service
public class CreateProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICQRSCreateProduct cqrsCreateProduct;

    @Autowired
    private CommandVersion commandVersion;

    @Transactional(rollbackFor = Exception.class)
    public Product createProduct(Product product) throws EcommerceBusinessLogicException {
        //genRandomContent(product);
        if(queryProductRepository.existsProductByIdOrSkuOrEan(product.getId(), product.getSku(), product.getEan())) throw ProductAlreadyExistsException.builder().build();
        product = Common.calculateFinalPriceAndOthers(product);
        product = commandProductRepository.save(product);
        cqrsCreateProduct.synchronize(setupProductVersion(product), product);
        return product;
    }

    @Transactional
    public Version setupProductVersion(Product product) throws EcommerceBusinessLogicException {
        Version version = new Version();
        version.setGroupId("p" + product.getId());
        version.setDescription("Version of product with id " + product.getId());
        version.setValue(1);
        return commandVersion.createVersion(version);
    }

    private void genRandomContent(Product product){
        String letters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890";
        String id = "", sku = "", ean = "", pn = "";

        Random random = new Random();

        for(int i = 0; i < 10; i++){
            id = id + letters.charAt(random.nextInt(letters.length()));
            sku = sku + letters.charAt(random.nextInt(letters.length()));
            ean = ean + letters.charAt(random.nextInt(letters.length()));
            pn = pn + letters.charAt(random.nextInt(letters.length()));
        }

        product.setId(id);
        product.setName(product.getId() + "#" + product.getName());
        product.setSku(sku);
        product.setEan(ean);
        product.setPn(pn);
    }
}

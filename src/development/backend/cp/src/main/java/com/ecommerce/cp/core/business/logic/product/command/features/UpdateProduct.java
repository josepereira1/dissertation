package com.ecommerce.cp.core.business.logic.product.command.features;

import com.ecommerce.cp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cp.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cp.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.exceptions.product.SomeArgumentsRelatedToPriceInFaultException;
import com.ecommerce.cp.core.ports.out.messaging.cqrs.product.ICQRSUpdateProduct;
import com.ecommerce.cp.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICQRSUpdateProduct cqrsUpdateProduct;

    @Autowired
    private CommandVersion commandVersion;

    @Transactional(rollbackFor = Exception.class)
    public Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException {
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        //if(queryProductRepository.existsProductBySkuOrEan(product.getSku(), product.getEan())) throw ProductAlreadyExistsException.builder().build();
        Product currentProduct = optional.get();
        product.setId(currentProduct.getId());  //  como vai ser enviado para o cqrs messaging, necessita-se de colocar o id, uma vez que, não vem no product
        updateProduct(product, currentProduct);
        if(product.getCurrency() != null || product.getInitialPrice() != null || product.getDiscountPercentage() != null || product.getVatPercentage() != null){
            currentProduct = Common.calculateFinalPriceAndOthers(currentProduct);
            product.setCurrency(currentProduct.getCurrency());
            product.setInitialPrice(currentProduct.getInitialPrice());
            product.setDiscountPercentage(currentProduct.getDiscountPercentage());
            product.setAmountInDiscount(currentProduct.getAmountInDiscount());
            product.setVatPercentage(currentProduct.getVatPercentage());
            product.setAmountInVat(currentProduct.getAmountInVat());
            product.setFinalPrice(currentProduct.getFinalPrice());
            product.setShipping(currentProduct.getShipping());
        }
        currentProduct = commandProductRepository.save(currentProduct);
        //  TODO pode ser interessante mandar o currentProduct, para ter sempre a certeza que tem todo o objeto sincronizado

        Version productVersion = commandVersion.generateVersion("p" + product.getId());

        cqrsUpdateProduct.synchronize(productVersion, currentProduct);
        return currentProduct;
    }

    private void updateProduct(Product productUpdated, Product product) {
        if(productUpdated.getName() != null) product.setName(productUpdated.getName());
        if(productUpdated.getShortDetails() != null) product.setShortDetails(productUpdated.getShortDetails());
        if(productUpdated.getLongDetails() != null)product.setLongDetails(productUpdated.getLongDetails());
        if(productUpdated.getCurrency() != null) product.setCurrency(productUpdated.getCurrency());
        if(productUpdated.getInitialPrice() != null) product.setInitialPrice(productUpdated.getInitialPrice());
        if(productUpdated.getDiscountPercentage() != null) product.setDiscountPercentage(productUpdated.getDiscountPercentage());
        if(productUpdated.getVatPercentage() != null) product.setVatPercentage(productUpdated.getVatPercentage());
        if(productUpdated.getShipping() != null) product.setShipping(productUpdated.getShipping());
        if(productUpdated.getStockStatus() != null) product.setStockStatus(productUpdated.getStockStatus());
        if(productUpdated.getLinks() != null) product.setLinks(productUpdated.getLinks());
        if(productUpdated.getVisibility() != null) product.setVisibility(productUpdated.getVisibility());
        if(productUpdated.getSku() != null) product.setSku(productUpdated.getSku());
        if(productUpdated.getEan() != null) product.setEan(productUpdated.getEan());
        if(productUpdated.getPn() != null) product.setPn(productUpdated.getPn());
        if(productUpdated.getOwner() != null) product.setOwner(productUpdated.getOwner());
        if(productUpdated.getCountermeasure() != null) product.setCountermeasure(productUpdated.getCountermeasure());
    }
}

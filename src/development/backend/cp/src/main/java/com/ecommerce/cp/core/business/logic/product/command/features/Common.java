package com.ecommerce.cp.core.business.logic.product.command.features;

import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.exceptions.product.SomeArgumentsRelatedToPriceInFaultException;

public class Common {

    public static Product calculateFinalPriceAndOthers(Product product) throws SomeArgumentsRelatedToPriceInFaultException {
        if(product.getInitialPrice() == null ||
                product.getDiscountPercentage() == null ||
                product.getVatPercentage() == null)
            throw SomeArgumentsRelatedToPriceInFaultException.builder().arguments("initial price, discount percentage, vat percentage").build();
        double discountPercentage = (product.getDiscountPercentage()/100);
        double vatPercentage = (product.getVatPercentage()/100);
        double amountInDiscount = product.getInitialPrice() * discountPercentage;
        double priceBeforeTax = product.getInitialPrice() - amountInDiscount;
        double finalPriceWithoutDiscount = product.getInitialPrice() * (1 + vatPercentage);
        double finalPrice = priceBeforeTax * (1 + vatPercentage);
        double amountInVat = finalPrice - priceBeforeTax;
        amountInDiscount = finalPriceWithoutDiscount - finalPrice;
        product.setAmountInDiscount(amountInDiscount);
        product.setAmountInVat(amountInVat);
        product.setFinalPrice(finalPrice);
        return product;
    }
}

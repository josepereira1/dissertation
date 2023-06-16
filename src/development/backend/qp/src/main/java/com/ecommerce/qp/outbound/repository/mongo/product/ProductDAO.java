package com.ecommerce.qp.outbound.repository.mongo.product;

import com.ecommerce.qp.core.business.resources.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends MongoRepository<Product, String> {

    //  TODO arranjar alternativa a este método, pois está a puxar toda a informação
    @Query(value = "{ 'id' : {'$in' : ?0 }}", fields = "{'id' : 1, 'name' : 1, 'currency' : 1, 'initialPrice' : 1, 'discountPercentage': 1, 'amountInDiscount': 1, 'vatPercentage' : 1, 'amountInVat': 1, 'finalPrice': 1, 'shipping': 1, 'sku' : 1, 'ean' : 1, 'pn' : 1}")
    Iterable<Product> findAllById(Iterable<String> ids);
}

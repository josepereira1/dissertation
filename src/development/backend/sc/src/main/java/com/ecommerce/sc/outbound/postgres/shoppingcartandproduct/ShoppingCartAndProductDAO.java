package com.ecommerce.sc.outbound.postgres.shoppingcartandproduct;

import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ShoppingCartAndProductDAO extends JpaRepository<ShoppingCartAndProduct, ShoppingCartAndProductId> {
}

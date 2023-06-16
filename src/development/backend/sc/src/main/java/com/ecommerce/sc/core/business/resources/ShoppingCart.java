package com.ecommerce.sc.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ShoppingCart implements Serializable {
    @Id
    private String id;
    private Integer size;
    private Double total;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ShoppingCartAndProduct> products;
}

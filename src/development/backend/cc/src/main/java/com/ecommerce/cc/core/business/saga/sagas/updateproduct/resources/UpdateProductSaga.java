package com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources;

import com.ecommerce.cc.core.business.saga.utils.resources.SagaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UpdateProductSaga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sagaId;
    private SagaStatus sagaStatus;

    //  DATA
    private String productId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_backup_id", referencedColumnName = "id")
    private UpdateProductSagaProductBackup productBackup;
}

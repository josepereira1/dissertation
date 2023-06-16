package com.ecommerce.cc.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Version {
    @Id
    private String groupId;
    private String description;
    private Integer value;
}

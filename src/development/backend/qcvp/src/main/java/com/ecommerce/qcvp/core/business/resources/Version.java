package com.ecommerce.qcvp.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Version {
    @Id
    private String groupId;
    private String description;
    private Integer value;
}

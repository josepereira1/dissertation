package com.ecommerce.manager.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nonapi.io.github.classgraph.json.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Manager {
    @Id
    private String id;
    private String password;
    private String email;
    private String token;

}

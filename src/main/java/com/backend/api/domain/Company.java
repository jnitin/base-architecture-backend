package com.backend.api.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company extends Base {
    @NotNull
    private String name;

    private String cnpj;

    @ManyToMany
    @JoinTable(name = "company_user", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @OneToMany(mappedBy = "company")
    private Set<Parameter> parameters;
}
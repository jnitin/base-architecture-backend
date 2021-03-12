package com.backend.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parameter extends Base {
    @NotNull
    private String key;

    @NotNull
    private String value;

    private String note;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
package com.backend.api.domain;

import com.backend.api.dto.update.UpdateParameterDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "parameters", fetch = FetchType.LAZY)
    private Set<Company> companies = new HashSet<>();

    public void update(UpdateParameterDto dto, Company c) {
        this.companies = Set.of(c);
        this.key = dto.getKey();
        this.value = dto.getValue();
        this.note = dto.getNote();
    }



}
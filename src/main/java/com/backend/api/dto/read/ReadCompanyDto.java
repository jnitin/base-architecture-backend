package com.backend.api.dto.read;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReadCompanyDto implements Serializable {
    private Long id;
    private String name;
    private String cnpj;
}
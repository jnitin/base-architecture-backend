package com.backend.api.dto.create;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class CreateCompanyDto implements Serializable {
    private String name;
    private String cnpj;
}
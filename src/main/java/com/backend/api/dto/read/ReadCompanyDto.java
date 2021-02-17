package com.backend.api.dto.read;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ReadCompanyDto implements Serializable {
    private Long id;
    private String name;
    private String cnpj;
}
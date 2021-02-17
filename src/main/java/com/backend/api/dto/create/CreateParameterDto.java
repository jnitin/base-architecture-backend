package com.backend.api.dto.create;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class CreateParameterDto implements Serializable {
    private Long id;
    private Integer company;
    private String key;
    private String value;
    private String note;
}
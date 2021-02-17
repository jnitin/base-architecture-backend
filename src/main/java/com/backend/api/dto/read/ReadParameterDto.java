package com.backend.api.dto.read;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ReadParameterDto implements Serializable {
    private Long id;
    private Integer company;
    private String key;
    private String value;
    private String note;
}
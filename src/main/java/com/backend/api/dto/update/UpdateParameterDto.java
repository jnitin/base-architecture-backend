package com.backend.api.dto.update;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class UpdateParameterDto implements Serializable {
    private Long id;
    private Long companyId;
    private String key;
    private String value;
    private String note;
}
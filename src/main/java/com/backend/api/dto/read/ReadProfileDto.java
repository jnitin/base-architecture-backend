package com.backend.api.dto.read;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ReadProfileDto implements Serializable {
    private Long id;
    private String description;
    private Integer level;
}
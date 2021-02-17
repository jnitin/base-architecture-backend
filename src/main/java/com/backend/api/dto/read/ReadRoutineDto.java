package com.backend.api.dto.read;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ReadRoutineDto implements Serializable {
    private String description;
    private Integer code;
}
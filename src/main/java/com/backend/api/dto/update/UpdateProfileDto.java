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
public class UpdateProfileDto implements Serializable {
    private Long id;
    private String description;
    private Integer level;
}
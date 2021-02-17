package com.backend.api.dto.read;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class ReadUserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer situation;
}
package com.backend.api.dto.create;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class CreateUserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer situation;
}
package com.backend.api.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class AddressDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer situation;
}
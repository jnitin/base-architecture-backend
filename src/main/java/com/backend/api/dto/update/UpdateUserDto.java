package com.backend.api.dto.update;

import com.backend.api.domain.enums.UserSituation;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class UpdateUserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserSituation situation;
}
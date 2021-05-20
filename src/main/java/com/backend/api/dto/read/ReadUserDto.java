package com.backend.api.dto.read;

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
public class ReadUserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private UserSituation situation;
}
package com.backend.api.dto.update;

import com.backend.api.domain.enums.RouteType;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class UpdateRouteDto implements Serializable {
    private String description;
    private RouteType type;
    private String url;
    private String icon;
    private String method;
    private String category;
}
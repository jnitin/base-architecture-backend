package com.backend.api.dto.create;

import com.backend.api.domain.Route;
import com.backend.api.domain.enums.RouteType;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class CreateRouteDto implements Serializable {
    private Long id;
    private String description;
    private RouteType type;
    private String url;
    private String icon;
    private Route father;
    private String method;
    private String category;
}
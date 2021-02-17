package com.backend.api.dto.read;

import com.backend.api.domain.Route;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ReadRouteDto implements Serializable {
    private Long id;
    private String description;
    private Integer type;
    private String url;
    private String icon;
    private Route father;
    private String method;
    private String category;
}
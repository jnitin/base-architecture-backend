package com.backend.api.domain;

import com.backend.api.domain.enums.RouteType;
import com.backend.api.dto.update.UpdateRouteDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Route extends Base {
    private String description;
    private String url;
    private String method;
    private String icon;
    private String category;

    @Enumerated(EnumType.STRING)
    private RouteType type;

    @ManyToOne
    private Route father;

    @ManyToMany(mappedBy = "routes")
    private Set<UserProfile> profiles;

    public void update(UpdateRouteDto dto) {
        description = dto.getDescription();
        type = dto.getType();
        url = dto.getUrl();
        icon = dto.getIcon();
        method = dto.getMethod();
        category = dto.getCategory();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Route other = (Route) obj;
        if (method == null) {
            if (other.method != null)
                return false;
        } else if (!method.equals(other.method))
            return false;
        if (url == null) {
            return other.url == null;
        } else return url.matches(other.url);
    }
}
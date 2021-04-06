package com.backend.api.pagination;

import com.backend.api.enums.SortDirection;
import lombok.Getter;
import lombok.Setter;
import org.apache.maven.surefire.util.internal.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class Pageable {
    private String orderBy;
    private SortDirection direction = SortDirection.ASC;
    private Integer page = 0;
    private Integer linesPerPage = 15;

    public PageRequest getPageable(PaginationPropertyMapper mapper) {
        if (StringUtils.isNotBlank(orderBy)) {
            return PageRequest.of(page, linesPerPage, Sort.by(getDirection(), mapper.interpolatePropertyPath(orderBy)));
        }

        return PageRequest.of(page, linesPerPage, mapper.defaultSort());
    }

    public PageRequest getPageable() {
        return getPageable(new DefaultPaginationPropertyMapper());
    }

    public Sort.Direction getDirection() {
        return Sort.Direction.fromString(direction.name());
    }

}

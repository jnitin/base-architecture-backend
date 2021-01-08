package com.backend.api.pagination;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public interface PaginationPropertyMapper {

    default String interpolatePropertyPath(String property) {
        return property;
    }

    default Sort defaultSort() {
        return Sort.by(Direction.DESC, "id");
    }
}
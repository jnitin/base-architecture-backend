package com.backend.api.pagination;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DefaultPaginationPropertyMapper implements PaginationPropertyMapper {

    @Override
    public String interpolatePropertyPath(String property) {
        return property;
    }
}

package com.backend.api.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Searchable extends Pageable {
    private String search;
}

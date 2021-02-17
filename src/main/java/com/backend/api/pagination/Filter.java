package com.backend.api.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filter extends Pageable {
    private String search;
}

package com.backend.api.mapper;

import org.springframework.data.domain.Page;

import java.util.List;

public interface DataMapper {
    <E, F> F mapTo(
        E input,
        Class<F> outputType
    );

    <E, F> List<F> mapAllTo(
        Iterable<? extends E> input,
        Class<F> outputType
    );

    <E, F> Page<F> mapAllTo(
        Page<? extends E> input,
        Class<F> outputType
    );
}

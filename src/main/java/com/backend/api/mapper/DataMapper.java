package com.backend.api.mapper;

public interface DataMapper {
    <E, F> F mapTo(
        E input,
        Class<F> outputType
    );
}

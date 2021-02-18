package com.backend.api.mapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class DataMapperImpl implements DataMapper {
    private final ModelMapper modelMapper;

    @Override
    public <E, F> F mapTo(
        E input,
        Class<F> outputType
    ) {
        return modelMapper.map(input, outputType);
    }
}

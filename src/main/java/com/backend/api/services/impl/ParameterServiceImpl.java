package com.backend.api.services.impl;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.create.CreateParameterDto;
import com.backend.api.pagination.Pageable;
import com.backend.api.repositories.ParameterRepository;
import com.backend.api.services.ParameterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ParameterServiceImpl implements ParameterService {

    private final ParameterRepository parameterRepository;

    @Override
    public Parameter create(CreateParameterDto createParameterDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Parameter findById(Long id) {
        return null;
    }

    @Override
    public void update(Long id, Object o) {

    }

    @Override
    public Page<Object> findAll(Pageable pageable) {
        return null;
    }
}

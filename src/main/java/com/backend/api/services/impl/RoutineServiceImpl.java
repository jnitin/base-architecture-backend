package com.backend.api.services.impl;

import com.backend.api.domain.Routine;
import com.backend.api.dto.create.CreateRoutineDto;
import com.backend.api.repositories.RoutineRepository;
import com.backend.api.services.RoutineService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.backend.api.pagination.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;

    @Override
    public Routine create(CreateRoutineDto createRoutineDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Routine findById(Long id) {
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

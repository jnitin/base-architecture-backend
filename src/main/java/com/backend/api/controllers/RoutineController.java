package com.backend.api.controllers;

import com.backend.api.domain.Routine;
import com.backend.api.dto.create.CreateRoutineDto;
import com.backend.api.dto.read.ReadRoutineDto;
import com.backend.api.pagination.Filter;
import com.backend.api.services.RoutineService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routines")
public class RoutineController extends CrudController<Routine, CreateRoutineDto, ReadRoutineDto, Object, Filter> {

    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        super(routineService);
        this.routineService = routineService;
    }
}
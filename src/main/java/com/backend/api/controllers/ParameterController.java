package com.backend.api.controllers;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.create.CreateParameterDto;
import com.backend.api.dto.read.ReadParameterDto;
import com.backend.api.dto.update.UpdateParameterDto;
import com.backend.api.pagination.Filter;
import com.backend.api.services.ParameterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parameters")
public class ParameterController extends CrudController<Parameter, CreateParameterDto, ReadParameterDto, UpdateParameterDto, Filter> {

    private final ParameterService parameterService;
    public ParameterController(ParameterService parameterService) {
        super(parameterService);
        this.parameterService = parameterService;
    }
}
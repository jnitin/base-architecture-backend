package com.backend.api.controllers;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.ParameterDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parameters")
public class ParameterController extends CrudController<Parameter, ParameterDTO> {

}
package com.backend.api.controllers;

import com.backend.api.domain.Routine;
import com.backend.api.dto.RoutineDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routines")
public class RoutineController extends CrudController<Routine, RoutineDTO> {

}
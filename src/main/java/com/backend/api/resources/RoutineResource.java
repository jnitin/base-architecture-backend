package com.backend.api.resources;

import com.backend.api.domain.Routine;
import com.backend.api.dto.RoutineDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routines")
public class RoutineResource extends CrudResource<Routine, RoutineDTO> {

}
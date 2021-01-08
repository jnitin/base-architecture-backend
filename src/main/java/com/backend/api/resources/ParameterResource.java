package com.backend.api.resources;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.ParameterDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parameters")
public class ParameterResource extends CrudResource<Parameter, ParameterDTO> {

}
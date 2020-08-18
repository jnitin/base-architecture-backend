package com.backend.api.resources;

import com.backend.api.domain.Parametro;
import com.backend.api.dto.ParametroDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parametros")
public class ParametroResource extends CrudResource<Parametro, ParametroDTO> {
}
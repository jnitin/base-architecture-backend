package com.backend.api.resources;

import com.backend.api.domain.Rotina;
import com.backend.api.dto.RotinaDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rotinas")
public class RotinaResource extends CrudResource<Rotina, RotinaDTO> {

}
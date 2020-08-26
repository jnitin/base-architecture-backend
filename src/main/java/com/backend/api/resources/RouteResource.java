package com.backend.api.resources;

import com.backend.api.domain.Rota;
import com.backend.api.dto.RouteDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rotas")
public class RouteResource extends CrudResource<Rota, RouteDTO> {

}
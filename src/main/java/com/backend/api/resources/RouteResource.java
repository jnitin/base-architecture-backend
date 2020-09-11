package com.backend.api.resources;

import com.backend.api.domain.Route;
import com.backend.api.dto.RouteDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rotas")
public class RouteResource extends CrudResource<Route, RouteDTO> {

}
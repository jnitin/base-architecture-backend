package com.backend.api.resources;

import java.util.List;

import com.backend.api.domain.Route;
import com.backend.api.dto.RouteDTO;
import com.backend.api.services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routes")
public class RouteResource extends CrudResource<Route, RouteDTO> {

   
    @Autowired
    private RouteService routeService;

    @RequestMapping(method = RequestMethod.GET, value = "/menus")
    public ResponseEntity<List<RouteDTO>> getMenus() {
        
        return ResponseEntity.ok().body(routeService.getMenus());
    }
    
}
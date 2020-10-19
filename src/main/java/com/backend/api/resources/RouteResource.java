package com.backend.api.resources;

import java.util.List;

import com.backend.api.domain.Route;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.dto.RouteDTO;
import com.backend.api.services.ProfileService;
import com.backend.api.services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routes")
public class RouteResource extends CrudResource<Route, RouteDTO> {

    @Autowired
    private RouteService routeService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET, value = "/menus")
    public ResponseEntity<List<RouteDTO>> getMenus() {
        return ResponseEntity.ok().body(routeService.getMenus());
    }

    @RequestMapping(value = "/profiles/{id}/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ProfileDTO>> getRoutes(@PathVariable Integer id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<ProfileDTO> profiles = routeService.getProfiles(id, page, linesPerPage, orderBy, direction)
                .map(profile -> profileService.toDTO(profile));
        return ResponseEntity.ok().body(profiles);
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.POST)
    public ResponseEntity<Page<ProfileDTO>> addProfiles(@PathVariable Integer id, @RequestBody List<Integer> ids) {
        this.routeService.insertProfiles(id, ids);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/profiles/{id}/{profileId}", method = RequestMethod.DELETE)
    public ResponseEntity<Page<ProfileDTO>> deleteProfile(@PathVariable Integer id, @PathVariable Integer profileId) {
        this.routeService.deleteProfile(id, profileId);
        return ResponseEntity.ok().build();
    }

}
package com.backend.api.controllers;

import com.backend.api.domain.Route;
import com.backend.api.dto.create.CreateRouteDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadRouteDto;
import com.backend.api.dto.update.UpdateRouteDto;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.services.ProfileService;
import com.backend.api.services.RouteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/routes")
public class RouteController extends CrudController<Route, CreateRouteDto, ReadRouteDto, UpdateRouteDto, Filter> {

    private final RouteService routeService;

    private final ProfileService profileService;

    public RouteController(RouteService routeService, RouteService routeService1, ProfileService profileService) {
        super(routeService);
        this.routeService = routeService1;
        this.profileService = profileService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/menus")
    public ResponseEntity<List<CreateRouteDto>> getMenus() {
        final var menus = routeService.getMenus();
        return ResponseEntity.ok().body(mapper.mapAllTo(menus, CreateRouteDto.class));
    }

    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.GET)
    public Page<ReadProfileDto> getProfiles(@PathVariable Long id, Pageable pageable) {
        return mapper.mapAllTo(routeService.findProfiles(id, pageable), ReadProfileDto.class);
    }

    @PostMapping(value = "/{id}/profiles")
    public ResponseEntity<Void> addProfiles(@PathVariable Long id, @RequestBody List<Long> ids) {
        routeService.linkProfiles(id, ids);
        return ResponseEntity.ok().build();
    }

        @DeleteMapping(value = "/{id}/profiles/{profileId}")
    public void deleteProfile(@PathVariable Long id, @PathVariable Long profileId) {
        routeService.deleteProfile(id, profileId);
    }

    @GetMapping(value = "/{id}/unlinked-profiles")
    public Page<ReadProfileDto> getUnlinkedProfiles(@PathVariable Long id, Pageable pageable) {
        return mapper.mapAllTo(routeService.findUnlinkedProfiles(id, pageable), ReadProfileDto.class);
    }

    public Page<ReadRouteDto> findRouteUnlinkedProfiles(@PathVariable Long id, Pageable pageable) {
        return mapper.mapAllTo(profileService.findUnlinkedRoutes(id, pageable), ReadRouteDto.class);
    }

}
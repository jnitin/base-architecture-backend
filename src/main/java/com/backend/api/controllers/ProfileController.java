package com.backend.api.controllers;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadRouteDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.services.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController extends CrudController<UserProfile, CreateProfileDto, ReadProfileDto, UpdateProfileDto, Filter> {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        super(profileService);
        this.profileService = profileService;
    }

    @GetMapping("/{id}/routes")
    public Page<ReadRouteDto> getRoutes(@PathVariable Long id, Pageable pageable) throws Exception {
        return mapper.mapAllTo(profileService.findLinkedRoutes(id, pageable), ReadRouteDto.class);
    }

    @PostMapping(value = "/{id}/routes")
    public void addRoutes(@PathVariable Long id, @RequestBody List<Long> ids) {
        profileService.saveRoutes(id, ids);
    }

    @DeleteMapping(value = "/{id}/routes/{routeId}")
    public void deleteRoute(@PathVariable Long id, @PathVariable Long routeId) {
        profileService.deleteRoute(id, routeId);
    }

    @GetMapping(value = "/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ReadUserDto>> getUsers(@PathVariable Long id, Pageable pageable) {
        Page<User> users = profileService.findProfileUsers(id, pageable);
        Page<ReadUserDto> page = mapper.mapAllTo(users, ReadUserDto.class);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping(value = "/{id}/users")
    public void addUsers(@PathVariable Long id, @RequestBody List<Long> ids) {
        profileService.addUsers(id, ids);
    }

    @DeleteMapping(value = "/{id}/users/{userId}")
    public void deleteUser(@PathVariable Long id, @PathVariable Long userId) {
        profileService.deleteUser(id, userId);
    }

    @GetMapping(value = "/{id}/unlinked-routes")
    public Page<ReadRouteDto> findProfileUnlinkedRoutes(@PathVariable Long id, Pageable pageable) {
        final Page<Route> routes = profileService.findUnlinkedRoutes(id, pageable);
        return mapper.mapAllTo(routes, ReadRouteDto.class);
    }

    @GetMapping(value = "/{id}/unlinked-users")
    public Page<ReadUserDto> findProfileUnlinkedUsers(@PathVariable Long id, Pageable pageable) {
        return mapper.mapAllTo(profileService.findUnlinkedUsers(id, pageable), ReadUserDto.class);
    }
}
package com.backend.api.controllers;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.dto.RouteDTO;
import com.backend.api.dto.UserDTO;
import com.backend.api.pagination.Pageable;
import com.backend.api.pagination.Searchable;
import com.backend.api.services.LinkableService;
import com.backend.api.services.RouteService;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileController extends CrudController<UserProfile, ProfileDTO> {

    private final RouteService routeService;
    private final UserService userService;

    @GetMapping("/{id}/routes")
    public ResponseEntity<Page<RouteDTO>> getRoutes(@PathVariable Integer id, Pageable pageable) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Page<RouteDTO> routes = new LinkableService<UserProfile, Route>(service, routeService)
                .getLinkedRecords(id, "getRoutes", pageable)
                .map(routeService::toDTO);
        return ResponseEntity.ok().body(routes);
    }

    @RequestMapping(value = "/{id}/routes", method = RequestMethod.POST)
    public ResponseEntity<Void> addRoutes(@PathVariable Integer id, @RequestBody List<Integer> ids)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<UserProfile, Route>(service, routeService).insert(id, ids, "getRoutes", "getUserProfiles",
                "Perfil não encontrado");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}/routes/{routeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRoute(@PathVariable Integer id, @PathVariable Integer routeId)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<UserProfile, Route>(service, routeService).delete(id, routeId, "getRoutes", "getUserProfiles",
                "Perfil não encontrado", "Rota não encontrada");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<UserDTO>> getUsers(@PathVariable Integer id, Pageable pageable) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Page<UserDTO> users = new LinkableService<UserProfile, User>(service, userService)
                .getLinkedRecords(id, "getUsers", pageable)
                .map(userService::toDTO);
        return ResponseEntity.ok().body(users);
    }

    @RequestMapping(value = "/{id}/users", method = RequestMethod.POST)
    public ResponseEntity<Void> addUsers(@PathVariable Integer id, @RequestBody List<Integer> ids)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<UserProfile, User>(service, userService).insert(id, ids, "getUsers", "getUserProfiles",
                "Perfil não encontrado");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id, @PathVariable Integer userId)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<UserProfile, User>(service, userService).delete(id, userId, "getUsers", "getUserProfiles",
                "Perfil não encontrado", "Rota não encontrada");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/unlinked-routes/{id}", method = RequestMethod.GET)
    public Page<ProfileDTO> findProfileUnlinkedRoutes(@PathVariable Integer id, Searchable searchable) {
        final List<UserProfile> notIn = routeService.getProfiles(id);

        return service.findPage(searchable, notIn, "routes");
    }

    @RequestMapping(value = "/unlinked-users/{id}", method = RequestMethod.GET)
    public Page<ProfileDTO> findProfileUnlinkedUsers(@PathVariable Integer id, Searchable searchable) {

        final List<UserProfile> notIn = userService.getUserProfiles(id);

        return service.findPage(searchable, notIn, "users");
    }
}
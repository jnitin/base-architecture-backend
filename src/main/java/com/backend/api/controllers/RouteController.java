package com.backend.api.controllers;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.dto.RouteDTO;
import com.backend.api.pagination.Pageable;
import com.backend.api.pagination.Searchable;
import com.backend.api.services.LinkableService;
import com.backend.api.services.ProfileService;
import com.backend.api.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping(value = "/routes")
public class RouteController extends CrudController<Route, RouteDTO> {

    @Autowired
    private RouteService routeService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET, value = "/menus")
    public ResponseEntity<List<RouteDTO>> getMenus() {
        return ResponseEntity.ok().body(routeService.getMenus());
    }

    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.GET)
    public ResponseEntity<Page<ProfileDTO>> getProfiles(@PathVariable Integer id, Pageable pageable) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {


        Page<ProfileDTO> profiles = new LinkableService<Route, UserProfile>(service, profileService).getLinkedRecords(id, "getProfiles", pageable)
                .map(profile -> profileService.toDTO(profile));
        return ResponseEntity.ok().body(profiles);
    }

    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.POST)
    public ResponseEntity<Void> addProfiles(@PathVariable Integer id, @RequestBody List<Integer> ids)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<Route, UserProfile>(service, profileService).insert(id, ids, "getUserProfiles", "getRoutes",
                "Rota não encontrada");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}/profiles/{profileId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProfile(@PathVariable Integer id, @PathVariable Integer profileId)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<Route, UserProfile>(service, profileService).delete(id, profileId, "getUserProfiles",
                "getRoutes", "Rota não encontrada", "Perfil não encontrado");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/unlinked-profiles/{id}", method = RequestMethod.GET)
    public Page<RouteDTO> findRouteUnlinkedProfiles(@PathVariable Integer id, Searchable searchable) {
        final List<Route> notIn = profileService.getRoutes(id);

        return service.findPage(searchable, notIn, "profiles");
    }

}
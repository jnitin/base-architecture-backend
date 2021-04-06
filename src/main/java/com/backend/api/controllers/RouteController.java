package com.backend.api.controllers;

import com.backend.api.domain.Route;
import com.backend.api.dto.create.CreateRouteDto;
import com.backend.api.dto.read.ReadRouteDto;
import com.backend.api.dto.update.UpdateRouteDto;
import com.backend.api.pagination.Filter;
import com.backend.api.services.ProfileService;
import com.backend.api.services.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

//    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.GET)
//    public ResponseEntity<Page<CreateProfileDto>> getProfiles(@PathVariable Long id, Pageable pageable) throws NoSuchMethodException,
//            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//
//
//        Page<CreateProfileDto> profiles = new LinkableService<Route, UserProfile>(service, profileService).getLinkedRecords(id, "getProfiles", pageable)
//                .map(profile -> profileService.toDTO(profile));
//        return ResponseEntity.ok().body(profiles);
//    }

//    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.POST)
//    public ResponseEntity<Void> addProfiles(@PathVariable Long id, @RequestBody List<Integer> ids)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<Route, UserProfile>(service, profileService).insert(id, ids, "getUserProfiles", "getRoutes",
//                "Rota não encontrada");
//        return ResponseEntity.ok().build();
//    }

//    @RequestMapping(value = "/{id}/profiles/{profileId}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteProfile(@PathVariable Long id, @PathVariable Integer profileId)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<Route, UserProfile>(service, profileService).delete(id, profileId, "getUserProfiles",
//                "getRoutes", "Rota não encontrada", "Perfil não encontrado");
//        return ResponseEntity.ok().build();
//    }
//
//    @RequestMapping(value = "/unlinked-profiles/{id}", method = RequestMethod.GET)
//    public Page<CreateRouteDto> findRouteUnlinkedProfiles(@PathVariable Long id, Filter filter) {
//        final List<Route> notIn = profileService.getRoutes(id);
//
//        return service.findPage(filter, notIn, "profiles");
//    }

}
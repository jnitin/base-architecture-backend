package com.backend.api.controllers;

import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.pagination.Filter;
import com.backend.api.services.ProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController extends CrudController<UserProfile, CreateProfileDto, ReadProfileDto, UpdateProfileDto, Filter> {

    public ProfileController(ProfileService profileService) {
        super(profileService);
    }

//    @GetMapping("/{id}/routes")
//    public ResponseEntity<Page<CreateRouteDto>> getRoutes(@PathVariable Long id, Pageable pageable) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        Page<CreateRouteDto> routes = new LinkableService<UserProfile, Route>(service, routeServiceImpl)
//                .getLinkedRecords(id, "getRoutes", pageable)
//                .map(routeServiceImpl::toDTO);
//        return ResponseEntity.ok().body(routes);
//    }

//    @RequestMapping(value = "/{id}/routes", method = RequestMethod.POST)
//    public ResponseEntity<Void> addRoutes(@PathVariable Long id, @RequestBody List<Integer> ids)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<UserProfile, Route>(service, routeServiceImpl).insert(id, ids, "getRoutes", "getUserProfiles",
//                "Perfil não encontrado");
//        return ResponseEntity.ok().build();
//    }

//    @RequestMapping(value = "/{id}/routes/{routeId}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteRoute(@PathVariable Long id, @PathVariable Integer routeId)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<UserProfile, Route>(service, routeServiceImpl).delete(id, routeId, "getRoutes", "getUserProfiles",
//                "Perfil não encontrado", "Rota não encontrada");
//        return ResponseEntity.ok().build();
//    }

//    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Page<CreateUserDto>> getUsers(@PathVariable Long id, Pageable pageable) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        Page<CreateUserDto> users = new LinkableService<UserProfile, User>(service, userServiceImpl)
//                .getLinkedRecords(id, "getUsers", pageable)
//                .map(userServiceImpl::toDTO);
//        return ResponseEntity.ok().body(users);
//    }

//    @RequestMapping(value = "/{id}/users", method = RequestMethod.POST)
//    public ResponseEntity<Void> addUsers(@PathVariable Long id, @RequestBody List<Integer> ids)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<UserProfile, User>(service, userServiceImpl).insert(id, ids, "getUsers", "getUserProfiles",
//                "Perfil não encontrado");
//        return ResponseEntity.ok().build();
//    }

//    @RequestMapping(value = "/{id}/users/{userId}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @PathVariable Integer userId)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<UserProfile, User>(service, userServiceImpl).delete(id, userId, "getUsers", "getUserProfiles",
//                "Perfil não encontrado", "Rota não encontrada");
//        return ResponseEntity.ok().build();
//    }

//    @RequestMapping(value = "/unlinked-routes/{id}", method = RequestMethod.GET)
//    public Page<CreateProfileDto> findProfileUnlinkedRoutes(@PathVariable Long id, Filter filter) {
//        final List<UserProfile> notIn = routeServiceImpl.getProfiles(id);
//
//        return service.findPage(filter, notIn, "routes");
//    }
//
//    @RequestMapping(value = "/unlinked-users/{id}", method = RequestMethod.GET)
//    public Page<CreateProfileDto> findProfileUnlinkedUsers(@PathVariable Long id, Filter filter) {
//
//        final List<UserProfile> notIn = userServiceImpl.getUserProfiles(id);
//
//        return service.findPage(filter, notIn, "users");
//    }
}
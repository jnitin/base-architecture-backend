package com.backend.api.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController extends CrudController<UserProfile, CreateProfileDto, ReadProfileDto, UpdateProfileDto, Filter> {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        super(profileService);
        this. profileService = profileService;
    }

    @GetMapping("/{id}/routes")
    public Page<ReadRouteDto> getRoutes(@PathVariable Long id, Pageable pageable) throws Exception {
        return mapper.mapAllTo(profileService.findLinkedRoutes(id, pageable), ReadRouteDto.class);
    }

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

//    @GetMapping(value = "/unlinked-routes/{id}")
//    public Page<CreateProfileDto> findProfileUnlinkedRoutes(@PathVariable Long id, Filter filter) {
//        final List<UserProfile> notIn = routeServiceImpl.getProfiles(id);
//
//        return service.findPage(filter, notIn, "routes");
//    }
//
    @GetMapping(value = "/{id}/unlinked-users")
    public Page<ReadUserDto> findProfileUnlinkedUsers(@PathVariable Long id, Pageable pageable) {
        return mapper.mapAllTo(profileService.findUnlinkedUsers(id, pageable), ReadUserDto.class);
    }
}
package com.backend.api.controllers;

import com.backend.api.domain.User;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.services.ProfileService;
import com.backend.api.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController extends CrudController<User, CreateUserDto, ReadUserDto, Object, Filter> {

    private final ProfileService profileService;

    private final UserService userService;

    public UserController(ProfileService profileService, UserService userService) {
        super(userService);
        this.profileService = profileService;
        this.userService = userService;
    }


//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Void> insert(@Valid @RequestBody CreateUserDto userDto) {
//        userDto.setPassword("123456");
//        userDto.setSituation(UserSituation.ACTIVE.getCod());
//        User obj = userService.fromDTO(userDto);
//        obj = userService.insert(obj);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }


    @GetMapping(value = "/{id}/profiles")
    public ResponseEntity<Page<ReadProfileDto>> getUserProfiles(@PathVariable Long id, Pageable pageable)  {
        Page<ReadProfileDto> profiles = mapper.mapAllTo(userService.findProfilesById(id, pageable), ReadProfileDto.class);
        return ResponseEntity.ok().body(profiles);
    }

//    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.POST)
//    public ResponseEntity<Void> addUserProfiles(@PathVariable Long id, @RequestBody List<Integer> ids)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<User, UserProfile>(service, profileServiceImpl).insert(id, ids, "getUserProfiles", "getUsers",
//                "Usuário não encontrado");
//        return ResponseEntity.ok().build();
//    }
//
//    @RequestMapping(value = "/{id}/profiles/{profileId}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteProfile(@PathVariable Long id, @PathVariable Integer profileId)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException {
//        new LinkableService<User, UserProfile>(service, profileServiceImpl).delete(id, profileId, "getUserProfiles", "getUsers",
//                "Usuário não encontrado", "Perfil não encontrado");
//        return ResponseEntity.ok().build();
//    }

    @GetMapping(value = "/{id}/unlinked-profiles")
    public Page<ReadProfileDto> findUnlinkedProfiles(@PathVariable Long id, Pageable pageable) {
        return mapper.mapAllTo(userService.findUnlinkedProfiles(id, pageable), ReadProfileDto.class);
    }

}
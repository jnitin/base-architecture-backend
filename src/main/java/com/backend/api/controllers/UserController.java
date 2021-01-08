package com.backend.api.controllers;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.dto.UserDTO;
import com.backend.api.pagination.Pageable;
import com.backend.api.pagination.Searchable;
import com.backend.api.services.LinkableService;
import com.backend.api.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends CrudController<User, UserDTO> {

    @Autowired
    ProfileService profileService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO userDto) {
        userDto.setPassword("123456");
        userDto.setSituation(UserSituation.ACTIVE);
        User obj = service.fromDTO(userDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.GET)
    public ResponseEntity<Page<ProfileDTO>> getUserProfiles(@PathVariable Integer id, Pageable pageable) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Page<ProfileDTO> profiles = new LinkableService<User, UserProfile>(service, profileService)
                .getLinkedRecords(id, "getUserProfiles", pageable)
                .map(profile -> profileService.toDTO(profile));
        return ResponseEntity.ok().body(profiles);
    }

    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserProfiles(@PathVariable Integer id, @RequestBody List<Integer> ids)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<User, UserProfile>(service, profileService).insert(id, ids, "getUserProfiles", "getUsers",
                "Usuário não encontrado");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}/profiles/{profileId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProfile(@PathVariable Integer id, @PathVariable Integer profileId)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<User, UserProfile>(service, profileService).delete(id, profileId, "getUserProfiles", "getUsers",
                "Usuário não encontrado", "Perfil não encontrado");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/unlinked-profiles/{id}", method = RequestMethod.GET)
    public Page<UserDTO> findProfileUnlinkedProfiles(@PathVariable Integer id, Searchable searchable) {

        final List<User> notIn = profileService.getUsers(id);

        return service.findPage(searchable, notIn, "profiles");
    }

}
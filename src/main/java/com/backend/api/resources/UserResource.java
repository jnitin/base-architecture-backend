package com.backend.api.resources;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.dto.UserDTO;
import com.backend.api.services.LinkableService;
import com.backend.api.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserResource extends CrudResource<User, UserDTO> {

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

  /**
   * Lista perfis de uma rota
   * 
   * @param id
   * @param page
   * @param linesPerPage
   * @param orderBy
   * @param direction
   * @return
   * @throws InvocationTargetException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws SecurityException
   * @throws NoSuchMethodException
   */
  @RequestMapping(value = "/{id}/profiles", method = RequestMethod.GET)
  public ResponseEntity<Page<ProfileDTO>> getUserProfiles(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) throws NoSuchMethodException,
      SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Page<ProfileDTO> profiles = new LinkableService<User, UserProfile>(service, profileService)
        .getLinkedRecords(id, "getUserProfiles", page, linesPerPage, orderBy, direction)
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
  public Page<UserDTO> findProfileUnlinkedProfiles(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "search", defaultValue = "") String search) {

    final List<User> notIn = profileService.getUsers(id);

    return service.findPage(page, linesPerPage, orderBy, direction, search, notIn, "profiles");
  }

}
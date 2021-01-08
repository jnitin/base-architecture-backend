package com.backend.api.resources;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.dto.RouteDTO;
import com.backend.api.dto.UserDTO;
import com.backend.api.services.LinkableService;
import com.backend.api.services.ProfileService;
import com.backend.api.services.RouteService;
import com.backend.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileResource extends CrudResource<UserProfile, ProfileDTO> {

  @Autowired
  RouteService routeService;

  @Autowired
  ProfileService profileService;

  @Autowired
  UserService userService;

  LinkableService<UserProfile, Route> routeLinkableService;

  @RequestMapping(value = "/{id}/routes", method = RequestMethod.GET)
  public ResponseEntity<Page<RouteDTO>> getRoutes(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) throws NoSuchMethodException,
      SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Page<RouteDTO> routes = new LinkableService<UserProfile, Route>(service, routeService)
        .getLinkedRecords(id, "getRoutes", page, linesPerPage, orderBy, direction)
        .map(route -> routeService.toDTO(route));
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
  public ResponseEntity<Page<UserDTO>> getUsers(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) throws NoSuchMethodException,
      SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Page<UserDTO> users = new LinkableService<UserProfile, User>(service, userService)
        .getLinkedRecords(id, "getUsers", page, linesPerPage, orderBy, direction)
        .map(user -> userService.toDTO(user));
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

  /**
   * Lista rotas não relacionadas ao perfil
   * 
   * @param page
   * @param linesPerPage
   * @param orderBy
   * @param direction
   * @param search
   * @return
   */
  @RequestMapping(value = "/unlinked-routes/{id}", method = RequestMethod.GET)
  public Page<ProfileDTO> findProfileUnlinkedRoutes(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "search", defaultValue = "") String search) {

    final List<UserProfile> notIn = routeService.getProfiles(id);

    return service.findPage(page, linesPerPage, orderBy, direction, search, notIn, "routes");
  }

  @RequestMapping(value = "/unlinked-users/{id}", method = RequestMethod.GET)
  public Page<ProfileDTO> findProfileUnlinkedUsers(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "search", defaultValue = "") String search) {

    final List<UserProfile> notIn = userService.getUserProfiles(id);

    return service.findPage(page, linesPerPage, orderBy, direction, search, notIn, "users");
  }
}
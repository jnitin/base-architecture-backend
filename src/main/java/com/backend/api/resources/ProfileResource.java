package com.backend.api.resources;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.dto.RouteDTO;
import com.backend.api.services.LinkableService;
import com.backend.api.services.ProfileService;
import com.backend.api.services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileResource extends CrudResource<UserProfile, ProfileDTO> {

  @Autowired
  RouteService routeService;

  @Autowired
  ProfileService profileService;

  LinkableService<UserProfile, Route> routeLinkableService;

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

  /**
   * Vincula um perfil a uma rota (pelo id da rota)
   * 
   * @param id
   * @param ids
   * @return
   * @throws InvocationTargetException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws SecurityException
   * @throws NoSuchMethodException
   */
  @RequestMapping(value = "/{id}/routes", method = RequestMethod.POST)
  public ResponseEntity<Void> addRoutes(@PathVariable Integer id, @RequestBody List<Integer> ids)
      throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    new LinkableService<UserProfile, Route>(service, routeService).insert(id, ids, "getRoutes", "getUserProfiles",
        "Perfil não encontrado");
    return ResponseEntity.ok().build();
  }

  /**
   * Apaga um vínculo entre rota e perfil (pelo id da rota)
   * 
   * @param id
   * @param routeId
   * @return
   */
  @RequestMapping(value = "/{id}/routes/{routeId}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteRoute(@PathVariable Integer id, @PathVariable Integer routeId)
      throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    new LinkableService<UserProfile, Route>(service, routeService).delete(id, routeId, "getRoutes", "getUserProfiles",
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
  public Page<ProfileDTO> findRouteUnlinkedProfiles(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "search", defaultValue = "") String search) {

    final List<UserProfile> notIn = routeService.getProfiles(id);

    return service.findPage(page, linesPerPage, orderBy, direction, search, notIn, "routes");
  }
}
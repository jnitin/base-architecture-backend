package com.backend.api.resources;

import java.util.List;

import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileResource extends CrudResource<UserProfile, ProfileDTO> {

  @Autowired
  RouteService routeService;

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
  public Page<ProfileDTO> findRouteUnlinedProfiles(@PathVariable Integer id,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "search", defaultValue = "") String search) {

    final List<UserProfile> notIn = routeService.getProfiles(id);

    return service.findPage(page, linesPerPage, orderBy, direction, search, notIn, "routes");
  }
}
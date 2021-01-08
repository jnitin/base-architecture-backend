package com.backend.api.resources;

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
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping(value = "/routes")
public class RouteResource extends CrudResource<Route, RouteDTO> {

    @Autowired
    private RouteService routeService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET, value = "/menus")
    public ResponseEntity<List<RouteDTO>> getMenus() {
        return ResponseEntity.ok().body(routeService.getMenus());
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
    public ResponseEntity<Page<ProfileDTO>> getProfiles(@PathVariable Integer id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {


        Page<ProfileDTO> profiles = new LinkableService<Route, UserProfile>(service, profileService).getLinkedRecords(id, "getProfiles", page, linesPerPage, orderBy, direction)
                .map(profile -> profileService.toDTO(profile));
        return ResponseEntity.ok().body(profiles);
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
    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.POST)
    public ResponseEntity<Void> addProfiles(@PathVariable Integer id, @RequestBody List<Integer> ids)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<Route, UserProfile>(service, profileService).insert(id, ids, "getUserProfiles", "getRoutes",
                "Rota não encontrada");
        return ResponseEntity.ok().build();
    }

    /**
     * Apaga um vínculo entre rota e perfil (pelo id da rota)
     * 
     * @param id
     * @param profileId
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    @RequestMapping(value = "/{id}/profiles/{profileId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProfile(@PathVariable Integer id, @PathVariable Integer profileId)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        new LinkableService<Route, UserProfile>(service, profileService).delete(id, profileId, "getUserProfiles",
                "getRoutes", "Rota não encontrada", "Perfil não encontrado");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/unlinked-profiles/{id}", method = RequestMethod.GET)
    public Page<RouteDTO> findRouteUnlinkedProfiles(@PathVariable Integer id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "search", defaultValue = "") String search) {

        final List<Route> notIn = profileService.getRoutes(id);

        return service.findPage(page, linesPerPage, orderBy, direction, search, notIn, "profiles");
    }

}
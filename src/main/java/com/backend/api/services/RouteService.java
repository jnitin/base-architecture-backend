package com.backend.api.services;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateRouteDto;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RouteService extends CrudService<Route, CreateRouteDto, Object, Object, Filter> {
    List<Route> getMenus();

    Page<UserProfile> getProfiles(Long id, Pageable pageable);

    List<UserProfile> getProfiles(Long id);
}

package com.backend.api.services;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateRouteDto;
import com.backend.api.dto.read.ReadRouteDto;
import com.backend.api.dto.update.UpdateRouteDto;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RouteService extends CrudService<Route, CreateRouteDto, ReadRouteDto, UpdateRouteDto, Filter> {
    List<Route> getMenus();

    Page<UserProfile> findProfiles(Long id, Pageable pageable);

    Page<UserProfile> findUnlinkedProfiles(Long id, Pageable pageable);

    void linkProfiles(Long id, List<Long> ids);
}

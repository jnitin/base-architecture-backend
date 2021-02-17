package com.backend.api.services.impl;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateRouteDto;
import com.backend.api.repositories.RouteRepository;
import com.backend.api.repositories.UserRepository;
import com.backend.api.security.UserSS;
import com.backend.api.services.RouteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.backend.api.pagination.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RouteServiceImpl implements RouteService {

    @Override
    public Route create(CreateRouteDto createRouteDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Route findById(Long id) {
        return null;
    }

    @Override
    public void update(Long id, Object o) {

    }

    @Override
    public Page<Object> findAll(Pageable pageable) {
        return null;
    }

    private final UserRepository userRepository;

    private final RouteRepository routeRepository;

    public List<Route> getMenus() {
        UserSS user = UserSSServiceImpl.authenticated();
        final var routes = userRepository.getMenus(user.getId());
        return routes;
    }

    public Page<UserProfile> getProfiles(Long id, Pageable pageable) {
        return routeRepository.getProfiles(id, pageable.getPageable());
    }

    public List<UserProfile> getProfiles(Long id) {
        return routeRepository.getProfiles(id);
    }
}

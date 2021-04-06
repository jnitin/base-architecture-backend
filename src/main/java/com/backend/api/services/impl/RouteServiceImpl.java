package com.backend.api.services.impl;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateRouteDto;
import com.backend.api.dto.read.ReadRouteDto;
import com.backend.api.dto.update.UpdateRouteDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
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

    final DataMapper mapper;
    final RouteRepository routeRepository;
    private final UserRepository userRepository;

    @Override
    public Route create(CreateRouteDto createRouteDto) {
        final var route = toEntity(createRouteDto);
        routeRepository.save(route);
        return route;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Route findById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Rota não encontrada."));
    }

    @Override
    public void update(Long id, UpdateRouteDto dto) {
        final Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Rota não encontrada."));

        route.update(dto);

        routeRepository.save(route);
    }


    @Override
    public Page<ReadRouteDto> findAll(Pageable pageable) {
        final var routes = routeRepository.findAll(pageable.getPageable());
        return mapper.mapAllTo(routes, ReadRouteDto.class);
    }

    @Override
    public Route toEntity(CreateRouteDto createRouteDto) {
        final var routeFather = findById(createRouteDto.getFather().getId());
        return Route
                .builder()
                .description(createRouteDto.getDescription())
                .type(createRouteDto.getType())
                .url(createRouteDto.getUrl())
                .icon(createRouteDto.getIcon())
                .father(routeFather)
                .method(createRouteDto.getMethod())
                .category(createRouteDto.getCategory())
                .build();
    }

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

package com.backend.api.services;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.api.domain.Route;
import com.backend.api.dto.RouteDTO;
import com.backend.api.repositories.UserRepository;
import com.backend.api.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService extends CrudService<Route, RouteDTO> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Route fromDTO(RouteDTO dto) {
		if (dto == null) {
			return null;
		}
		final Route obj = new Route(null, dto.getDescription(), dto.getTipo(), dto.getUrl(), dto.getIcone(), dto.getPai(), dto.getMethod(), dto.getCategory());
		return obj;
	}

	@Override
	public RouteDTO toDTO(Route obj) {
		if (obj == null) {
			return null;
		}
		final RouteDTO dto = new RouteDTO(obj.getDescription(), obj.getTipo(), obj.getUrl(), obj.getIcone(), obj.getPai(), obj.getMethod(), obj.getCategory());
		return dto;
	}

	public List<RouteDTO> getMenus() {
		UserSS user = UserSSService.authenticated();

		final List<RouteDTO> convertedList = userRepository.getMenus(user.getId()).stream().map(route -> toDTO(route)).collect(Collectors.toList());
		
		return convertedList;
	}

}

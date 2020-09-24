package com.backend.api.services;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.api.domain.Route;
import com.backend.api.domain.enums.RouteType;
import com.backend.api.dto.RouteDTO;
import com.backend.api.repositories.UserRepository;
import com.backend.api.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService extends CrudService<Route, RouteDTO> {
	
	public RouteService() {
		super(Route.class, RouteDTO.class);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public Route fromDTO(RouteDTO dto) {
		if (dto == null) {
			return null;
		}
		final Route obj = new Route(dto.getId(), dto.getDescription(), RouteType.toEnum(dto.getType()), dto.getUrl(), dto.getIcon(), dto.getFather(), dto.getMethod(), dto.getCategory());
		return obj;
	}

	@Override
	public RouteDTO toDTO(Route obj) {
		if (obj == null) {
			return null;
		}
		final RouteDTO dto = new RouteDTO(obj.getId(), obj.getDescription(), obj.getType().getCod(), obj.getUrl(), obj.getIcon(), obj.getFather(), obj.getMethod(), obj.getCategory());
		return dto;
	}

	public List<RouteDTO> getMenus() {
		UserSS user = UserSSService.authenticated();

		final List<RouteDTO> convertedList = userRepository.getMenus(user.getId()).stream().map(route -> toDTO(route)).collect(Collectors.toList());
		
		return convertedList;
	}

}

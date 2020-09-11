package com.backend.api.services;

import com.backend.api.domain.Route;
import com.backend.api.dto.RouteDTO;

import org.springframework.stereotype.Service;

@Service
public class RouteService extends CrudService<Route, RouteDTO> {

	@Override
	public Route fromDTO(RouteDTO dto) {
		if (dto == null) {
			return null;
		}
		final Route obj = new Route(null, dto.getDescription(), dto.getTipo(), dto.getUrl(), dto.getIcone(), dto.getPai(), dto.getMethod());
		return obj;
	}

	@Override
	public RouteDTO toDTO(Route obj) {
		if (obj == null) {
			return null;
		}
		final RouteDTO dto = new RouteDTO(obj.getDescription(), obj.getTipo(), obj.getUrl(), obj.getIcone(), obj.getPai(), obj.getMethod());
		return dto;
	}

}

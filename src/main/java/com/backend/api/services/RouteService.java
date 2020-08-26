package com.backend.api.services;

import com.backend.api.domain.Rota;
import com.backend.api.dto.RouteDTO;

import org.springframework.stereotype.Service;

@Service
public class RouteService extends CrudService<Rota, RouteDTO> {

	@Override
	public Rota fromDTO(RouteDTO dto) {
		if (dto == null) {
			return null;
		}
		final Rota obj = new Rota(null, dto.getDescricao(), dto.getTipo(), dto.getUrl(), dto.getIcone(), dto.getPai(), dto.getMethod());
		return obj;
	}

	@Override
	public RouteDTO toDTO(Rota obj) {
		if (obj == null) {
			return null;
		}
		final RouteDTO dto = new RouteDTO(obj.getDescricao(), obj.getTipo(), obj.getUrl(), obj.getIcone(), obj.getPai(), obj.getMethod());
		return dto;
	}

}

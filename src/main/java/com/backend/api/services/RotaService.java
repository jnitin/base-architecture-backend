package com.backend.api.services;

import com.backend.api.domain.Rota;
import com.backend.api.dto.RotaDTO;

import org.springframework.stereotype.Service;

@Service
public class RotaService extends CrudService<Rota, RotaDTO> {

	@Override
	public Rota fromDTO(RotaDTO dto) {
		if (dto == null) {
			return null;
		}
		final Rota obj = new Rota(null, dto.getDescricao(), dto.getTipo(), dto.getUrl(), dto.getIcone(), dto.getPai())
		return obj;
	}

	@Override
	public RotaDTO toDTO(Rota obj) {
		if (obj == null) {
			return null;
		}
		final RotaDTO dto = new RotaDTO(obj.getDescricao(), obj.getTipo(), obj.getUrl(), obj.getIcone(), obj.getPai())
		return dto;
	}

}

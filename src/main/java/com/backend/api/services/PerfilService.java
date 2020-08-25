package com.backend.api.services;

import com.backend.api.domain.Perfil;
import com.backend.api.dto.PerfilDTO;

import org.springframework.stereotype.Service;

@Service
public class PerfilService extends CrudService<Perfil, PerfilDTO> {

	@Override
	public Perfil fromDTO(PerfilDTO dto) {
		if (dto == null) {
			return null;
		}
		final Perfil obj = new Perfil(null, dto.getDescricao(), dto.getNivel());
		return obj;
	}

	@Override
	public PerfilDTO toDTO(Perfil obj) {
		if (obj == null) {
			return null;
		}
		final PerfilDTO dto = new PerfilDTO(obj.getDescricao(), obj.getNivel());
		return dto;
	}

}

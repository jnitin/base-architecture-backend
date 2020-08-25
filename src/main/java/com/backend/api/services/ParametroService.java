package com.backend.api.services;

import com.backend.api.domain.Parametro;
import com.backend.api.dto.ParametroDTO;

import org.springframework.stereotype.Service;

@Service
public class ParametroService extends CrudService<Parametro, ParametroDTO> {

	@Override
	public Parametro fromDTO(ParametroDTO dto) {
		if (dto == null) {
			return null;
		}
		final Parametro obj = new Parametro(null, dto.getChave(), dto.getValor(), dto.getObservacao());
		return obj;
	}

	@Override
	public ParametroDTO toDTO(Parametro obj) {
		if (obj == null) {
			return null;
		}
		final ParametroDTO dto = new ParametroDTO(obj.getChave(), obj.getValor(), obj.getObservacao());
		return dto;
	}

}

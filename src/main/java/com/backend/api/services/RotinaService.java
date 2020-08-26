package com.backend.api.services;

import com.backend.api.domain.Rotina;
import com.backend.api.dto.RotinaDTO;

import org.springframework.stereotype.Service;

@Service
public class RotinaService extends CrudService<Rotina, RotinaDTO> {

	@Override
	public Rotina fromDTO(RotinaDTO dto) {
		if (dto == null) {
			return null;
		}
		final Rotina obj = new Rotina(null, dto.getDescricao(), dto.getCodigo());
		return obj;
	}

	@Override
	public RotinaDTO toDTO(Rotina obj) {
		if (obj == null) {
			return null;
		}
		final RotinaDTO dto = new RotinaDTO(obj.getDescricao(), obj.getCodigo());
		return dto;
	}

}

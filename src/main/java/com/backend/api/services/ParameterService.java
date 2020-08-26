package com.backend.api.services;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.ParameterDTO;

import org.springframework.stereotype.Service;

@Service
public class ParameterService extends CrudService<Parameter, ParameterDTO> {

	@Override
	public Parameter fromDTO(ParameterDTO dto) {
		if (dto == null) {
			return null;
		}
		final Parameter obj = new Parameter(null, dto.getChave(), dto.getValor(), dto.getObservacao());
		return obj;
	}

	@Override
	public ParameterDTO toDTO(Parameter obj) {
		if (obj == null) {
			return null;
		}
		final ParameterDTO dto = new ParameterDTO(obj.getChave(), obj.getValor(), obj.getObservacao());
		return dto;
	}

}

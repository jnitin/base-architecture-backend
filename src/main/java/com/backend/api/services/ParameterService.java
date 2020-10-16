package com.backend.api.services;

import com.backend.api.domain.Company;
import com.backend.api.domain.Parameter;
import com.backend.api.dto.ParameterDTO;

import org.springframework.stereotype.Service;

@Service
public class ParameterService extends CrudService<Parameter, ParameterDTO> {

	public ParameterService() {
		super(Parameter.class, ParameterDTO.class);
	}

	@Override
	public Parameter fromDTO(ParameterDTO dto) {
		if (dto == null) {
			return null;
		}
		final Company c = new Company();
		c.setId(dto.getCompany());
		final Parameter obj = new Parameter(dto.getId(), dto.getKey(), dto.getValue(), dto.getNote(), c);
		return obj;
	}

	@Override
	public ParameterDTO toDTO(Parameter obj) {
		if (obj == null) {
			return null;
		}
		final ParameterDTO dto = new ParameterDTO(obj.getId(), obj.getKey(), obj.getValue(), obj.getNote(), obj.getCompany().getId());
		return dto;
	}

}

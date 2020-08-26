package com.backend.api.services;

import com.backend.api.domain.Enterprise;
import com.backend.api.dto.EnterpriseDTO;

import org.springframework.stereotype.Service;

@Service
public class EnterpriseService extends CrudService<Enterprise, EnterpriseDTO> {

	@Override
	public Enterprise fromDTO(EnterpriseDTO dto) {
		if (dto == null) {
			return null;
		}
		final Enterprise obj = new Enterprise(null, dto.getNome(), dto.getCnpj());
		return obj;
	}

	@Override
	public EnterpriseDTO toDTO(Enterprise obj) {
		if (obj == null) {
			return null;
		}
		final EnterpriseDTO dto = new EnterpriseDTO(obj.getNome(), obj.getCnpj());
		return dto;
	}

}

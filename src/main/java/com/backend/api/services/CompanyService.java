package com.backend.api.services;

import com.backend.api.domain.Company;
import com.backend.api.dto.CompanyDTO;

import org.springframework.stereotype.Service;

@Service
public class CompanyService extends CrudService<Company, CompanyDTO> {

	public CompanyService() {
		super(Company.class, CompanyDTO.class);
	}

	@Override
	public Company fromDTO(CompanyDTO dto) {
		if (dto == null) {
			return null;
		}
		final Company obj = new Company(null, dto.getName(), dto.getCnpj());
		return obj;
	}

	@Override
	public CompanyDTO toDTO(Company obj) {
		if (obj == null) {
			return null;
		}
		final CompanyDTO dto = new CompanyDTO(obj.getName(), obj.getCnpj());
		return dto;
	}

}

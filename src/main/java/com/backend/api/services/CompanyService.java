package com.backend.api.services;

import java.util.List;

import com.backend.api.domain.Company;
import com.backend.api.dto.CompanyDTO;
import com.backend.api.repositories.CompanyRepository;
import com.backend.api.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends CrudService<Company, CompanyDTO> {

	@Autowired
	protected CompanyRepository repo;

	public CompanyService() {
		super(Company.class, CompanyDTO.class);
	}

	@Override
	public Company fromDTO(CompanyDTO dto) {
		if (dto == null) {
			return null;
		}
		final Company obj = new Company(dto.getId(), dto.getName(), dto.getCnpj());
		return obj;
	}

	@Override
	public CompanyDTO toDTO(Company obj) {
		if (obj == null) {
			return null;
		}
		final CompanyDTO dto = new CompanyDTO(obj.getId(), obj.getName(), obj.getCnpj());
		return dto;
	}

	public List<CompanyDTO> getMenus() {
		UserSS user = UserSSService.authenticated();
		return toDTO(repo.getMenus(user.getId()));
	}

}

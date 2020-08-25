package com.backend.api.services;

import com.backend.api.domain.Empresa;
import com.backend.api.dto.EmpresaDTO;

import org.springframework.stereotype.Service;

@Service
public class EmpresaService extends CrudService<Empresa, EmpresaDTO> {

	@Override
	public Empresa fromDTO(EmpresaDTO dto) {
		if (dto == null) {
			return null;
		}
		final Empresa obj = new Empresa(null, dto.getNome(), dto.getCnpj());
		return obj;
	}

	@Override
	public EmpresaDTO toDTO(Empresa obj) {
		if (obj == null) {
			return null;
		}
		final EmpresaDTO dto = new EmpresaDTO(obj.getNome(), obj.getCnpj());
		return dto;
	}

}

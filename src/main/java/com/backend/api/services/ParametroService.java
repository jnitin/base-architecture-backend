package com.backend.api.services;

import java.util.List;

import com.backend.api.domain.Parametro;
import com.backend.api.dto.ParametroDTO;
import com.backend.api.repositories.ParametroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ParametroService extends CrudService<Parametro, ParametroDTO> {

	// @Autowired
	// private ParametroRepository repo;

	@Override
	public Parametro fromDTO(ParametroDTO obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParametroDTO toDTO(Parametro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parametro insert(Parametro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parametro update(Parametro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Parametro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parametro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

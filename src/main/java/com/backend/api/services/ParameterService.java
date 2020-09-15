package com.backend.api.services;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.ParameterDTO;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ParameterService extends CrudService<Parameter, ParameterDTO> {

	@Override
	public Parameter fromDTO(ParameterDTO dto) {
		if (dto == null) {
			return null;
		}
		final Parameter obj = new Parameter(null, dto.getKey(), dto.getValue(), dto.getNote());
		return obj;
	}

	@Override
	public ParameterDTO toDTO(Parameter obj) {
		if (obj == null) {
			return null;
		}
		final ParameterDTO dto = new ParameterDTO(obj.getKey(), obj.getValue(), obj.getNote());
		return dto;
	}

	@Override
	public Page<Parameter> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		System.out.println("fufufu");
		Parameter p = new Parameter();
		p.setKey("Maria");
		ExampleMatcher em = ExampleMatcher.matchingAll().withMatcher("key",ExampleMatcher.GenericPropertyMatchers.contains());
		Example<Parameter> e = Example.of(p, em);
		System.out.println(repo.findAll(e));
		return repo.findAll(e, pageRequest);
	}
}

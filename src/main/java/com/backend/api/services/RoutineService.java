package com.backend.api.services;

import com.backend.api.domain.Routine;
import com.backend.api.dto.RoutineDTO;
import org.springframework.stereotype.Service;

@Service
public class RoutineService extends CrudService<Routine, RoutineDTO> {

	public RoutineService() {
		super(Routine.class, RoutineDTO.class);
	}

	@Override
	public Routine fromDTO(RoutineDTO dto) {
		if (dto == null) {
			return null;
		}
		final Routine obj = new Routine(null, dto.getDescription(), dto.getCode());
		return obj;
	}

	@Override
	public RoutineDTO toDTO(Routine obj) {
		if (obj == null) {
			return null;
		}
		final RoutineDTO dto = new RoutineDTO(obj.getDescription(), obj.getCode());
		return dto;
	}

}

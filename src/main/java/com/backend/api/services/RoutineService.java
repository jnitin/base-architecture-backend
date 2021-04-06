package com.backend.api.services;

import com.backend.api.domain.Routine;
import com.backend.api.dto.create.CreateRoutineDto;
import com.backend.api.dto.read.ReadRoutineDto;
import com.backend.api.dto.update.UpdateRoutineDto;
import com.backend.api.pagination.Filter;

public interface RoutineService extends CrudService<Routine, CreateRoutineDto, ReadRoutineDto, UpdateRoutineDto, Filter> {

}

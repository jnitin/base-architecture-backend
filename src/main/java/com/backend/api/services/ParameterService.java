package com.backend.api.services;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.create.CreateParameterDto;
import com.backend.api.dto.read.ReadParameterDto;
import com.backend.api.dto.update.UpdateParameterDto;
import com.backend.api.pagination.Filter;

public interface ParameterService extends CrudService<Parameter, CreateParameterDto, ReadParameterDto, UpdateParameterDto, Filter> {

}

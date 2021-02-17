package com.backend.api.services;

import com.backend.api.domain.Parameter;
import com.backend.api.dto.create.CreateParameterDto;
import com.backend.api.pagination.Filter;

public interface ParameterService extends CrudService<Parameter, CreateParameterDto, Object, Object, Filter> {

}

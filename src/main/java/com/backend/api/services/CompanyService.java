package com.backend.api.services;

import com.backend.api.domain.Company;
import com.backend.api.dto.create.CreateCompanyDto;
import com.backend.api.pagination.Filter;

public interface CompanyService extends CrudService<Company, CreateCompanyDto, Object, Object, Filter> {

}

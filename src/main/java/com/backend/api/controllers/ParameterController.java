package com.backend.api.controllers;

import com.backend.api.annotation.security.CrudPermission;
import com.backend.api.domain.Company;
import com.backend.api.domain.Parameter;
import com.backend.api.dto.create.CreateParameterDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.read.ReadParameterDto;
import com.backend.api.dto.update.UpdateParameterDto;
import com.backend.api.enums.Permission;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Filter;
import com.backend.api.query.LinkerQuery;
import com.backend.api.query.impl.LinkerQueryImpl;
import com.backend.api.services.ParameterService;
import com.backend.api.services.impl.CrudLinkerServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping(value = "/parameters")
@CrudPermission(
    listAll = Permission.LIST_ALL_PARAMETERS,
    update = Permission.UPDATE_PARAMETERS,
    findById = Permission.FIND_BY_ID_PARAMETERS,
    delete = Permission.DELETE_PARAMETERS,
    create = Permission.CREATE_PARAMETERS
)
public class ParameterController
    extends CrudController<Parameter, CreateParameterDto, ReadParameterDto, UpdateParameterDto, Filter> {

  private final ParameterService parameterService;

  public ParameterController(ParameterService parameterService) {
    super(parameterService);
    this.parameterService = parameterService;
  }


  @RestController
  @RequestMapping(value = "/parameters/{id}/companies")
  static
  class CompaniesLink extends CrudLinkerController<Parameter, Company, ReadCompanyDto> {

    public CompaniesLink(CrudLinkerServiceImpl<Parameter, Company, ReadCompanyDto> service, DataMapper mapper, EntityManager entityManager) {
      super(service, mapper);
      LinkerQuery<Parameter, Company, ReadCompanyDto> query = new LinkerQueryImpl<Parameter, Company, ReadCompanyDto>(entityManager, Parameter.class, Company.class,ReadCompanyDto.class);
      service.setQuery(query);
    }

  }
}
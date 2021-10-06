package com.backend.api.controllers;

import com.backend.api.domain.Company;
import com.backend.api.domain.Parameter;
import com.backend.api.dto.create.CreateCompanyDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.read.ReadParameterDto;
import com.backend.api.dto.update.UpdateCompanyDto;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Filter;
import com.backend.api.query.LinkedQuery;
import com.backend.api.query.impl.LinkedQueryImpl;
import com.backend.api.services.CompanyService;
import com.backend.api.services.UserService;
import com.backend.api.services.impl.CrudLinkerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController extends CrudController<Company, CreateCompanyDto, ReadCompanyDto, UpdateCompanyDto, Filter> {

  private final CompanyService companyService;
  private final UserService userService;

  public CompanyController(CompanyService companyService, UserService userService) {
    super(companyService);
    this.companyService = companyService;
    this.userService = userService;
  }

  @GetMapping("/menus")
  public List<ReadCompanyDto> getMenus() {
    return companyService.getMenus(UserService.getLoggedInUser());
  }

  @RequestMapping(value = "/companies")
  static class ParametersLink extends CrudLinkerController<Company, Parameter, ReadParameterDto> {

    public ParametersLink(CrudLinkerServiceImpl<Company, Parameter, ReadParameterDto> service, DataMapper mapper, EntityManager entityManager) {
      super(service, mapper);

      LinkedQuery<Company, Parameter, ReadParameterDto> query = new LinkedQueryImpl(entityManager, Company.class, Parameter.class, ReadParameterDto.class);
      service.setQuery(query);
    }
  }
}
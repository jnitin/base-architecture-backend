package com.backend.api.controllers;

import com.backend.api.domain.Company;
import com.backend.api.dto.create.CreateCompanyDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.pagination.Filter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController extends CrudController<Company, CreateCompanyDto, ReadCompanyDto, Object, Filter> {

}
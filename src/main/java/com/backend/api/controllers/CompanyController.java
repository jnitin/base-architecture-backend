package com.backend.api.controllers;

import com.backend.api.domain.Company;
import com.backend.api.dto.create.CreateCompanyDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.update.UpdateCompanyDto;
import com.backend.api.pagination.Filter;
import com.backend.api.services.CompanyService;
import com.backend.api.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return companyService.getMenus(userService.getLoggedInUser());
    }
}
package com.backend.api.resources;

import com.backend.api.domain.Company;
import com.backend.api.dto.CompanyDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/companies")
public class CompanyResource extends CrudResource<Company, CompanyDTO> {

}
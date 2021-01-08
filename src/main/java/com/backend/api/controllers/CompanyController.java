package com.backend.api.controllers;

import com.backend.api.domain.Company;
import com.backend.api.dto.CompanyDTO;
import com.backend.api.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController extends CrudController<Company, CompanyDTO> {

    @Autowired
    protected CompanyService service;

    @RequestMapping(method = RequestMethod.GET, value = "/menus")
    public ResponseEntity<List<CompanyDTO>> menus() {
        List<CompanyDTO> obj = service.getMenus();
        return ResponseEntity.ok().body(obj);
    }

}
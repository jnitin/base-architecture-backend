package com.backend.api.resources;

import java.util.List;

import com.backend.api.domain.Company;
import com.backend.api.dto.CompanyDTO;
import com.backend.api.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/companies")
public class CompanyResource extends CrudResource<Company, CompanyDTO> {

  @Autowired
  protected CompanyService service;
    
  @RequestMapping(method = RequestMethod.GET, value = "/menus")
  public ResponseEntity<List<CompanyDTO>> menus() {
    List<CompanyDTO> obj = service.getMenus();
    return ResponseEntity.ok().body(obj);
  }

}
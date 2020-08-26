package com.backend.api.resources;

import com.backend.api.domain.Enterprise;
import com.backend.api.dto.EnterpriseDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/empresas")
public class EnterpriseResource extends CrudResource<Enterprise, EnterpriseDTO> {

}
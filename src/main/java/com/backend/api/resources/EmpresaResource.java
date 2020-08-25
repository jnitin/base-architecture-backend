package com.backend.api.resources;

import com.backend.api.domain.Empresa;
import com.backend.api.dto.EmpresaDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaResource extends CrudResource<Empresa, EmpresaDTO> {

}
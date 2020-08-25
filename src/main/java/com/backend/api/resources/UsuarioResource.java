package com.backend.api.resources;

import com.backend.api.domain.Usuario;
import com.backend.api.dto.UsuarioDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource extends CrudResource<Usuario, UsuarioDTO> {

}
package com.backend.api.resources;

import com.backend.api.domain.Perfil;
import com.backend.api.dto.PerfilDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/perfis")
public class PerfilResource extends CrudResource<Perfil, PerfilDTO> {

}
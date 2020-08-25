package com.backend.api.services;

import com.backend.api.domain.Usuario;
import com.backend.api.dto.UsuarioDTO;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends CrudService<Usuario, UsuarioDTO> {

	@Override
	public Usuario fromDTO(UsuarioDTO dto) {
		if (dto == null) {
			return null;
		}
		final Usuario obj = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getSituacao());
		return obj;
	}

	@Override
	public UsuarioDTO toDTO(Usuario obj) {
		if (obj == null) {
			return null;
		}
		final UsuarioDTO dto = new UsuarioDTO(obj.getNome(), obj.getEmail(), obj.getSenha(), obj.getSituacao());
		return dto;
	}

}

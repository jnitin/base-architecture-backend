package com.backend.api.services;

import com.backend.api.domain.User;
import com.backend.api.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, UserDTO> {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	public User fromDTO(UserDTO dto) {
		if (dto == null) {
			return null;
		}
		String senha = pe.encode(dto.getSenha());
		final User obj = new User(null, dto.getNome(), dto.getEmail(), senha, dto.getSituacao());
		return obj;
	}

	@Override
	public UserDTO toDTO(User obj) {
		if (obj == null) {
			return null;
		}
		final UserDTO dto = new UserDTO(obj.getNome(), obj.getEmail(), obj.getSenha(), obj.getSituacao());
		return dto;
	}

}

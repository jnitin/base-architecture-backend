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
		String senha = pe.encode(dto.getPassword());
		final User obj = new User(null, dto.getName(), dto.getEmail(), senha, dto.getSituation());
		return obj;
	}

	@Override
	public UserDTO toDTO(User obj) {
		if (obj == null) {
			return null;
		}
		final UserDTO dto = new UserDTO(obj.getName(), obj.getEmail(), obj.getPassword(), obj.getSituation());
		return dto;
	}

}

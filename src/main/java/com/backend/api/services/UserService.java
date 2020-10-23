package com.backend.api.services;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.UserDTO;
import com.backend.api.repositories.UserRepository;
import com.backend.api.services.exceptions.DataIntegrityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, UserDTO> {

	public UserService() {
		super(User.class, UserDTO.class);
	}

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	UserRepository userRepository;

	@Override
	public User fromDTO(UserDTO dto) {
		if (dto == null) {
			return null;
		}
		String password = pe.encode(dto.getPassword());
		final User obj = new User(dto.getId(), dto.getName(), dto.getEmail(), password, dto.getSituation());
		return obj;
	}

	@Override
	public UserDTO toDTO(User obj) {
		if (obj == null) {
			return null;
		}
		final UserDTO dto = new UserDTO(obj.getId(), obj.getName(), obj.getEmail(), obj.getPassword(), obj.getSituation());
		return dto;
	}

	public User insert(final User obj) {
		obj.setId(null);

		try {
			return this.repo.save(obj);

		} catch (ConstraintViolationException e) {
			throw new DataIntegrityException(
					"Erro ao salvar registro no sistema, algum campo está preenchido incorretamente");
		}
	}

	public List<UserProfile> getUserProfiles(Integer id) {
		return userRepository.getUserProfiles(id);
	}

	public Page<UserProfile> getUserProfiles(Integer id, PageRequest pageRequest) {
		return userRepository.getUserProfiles(id, pageRequest);
	}

}

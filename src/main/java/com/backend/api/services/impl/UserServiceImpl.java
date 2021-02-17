package com.backend.api.services.impl;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.repositories.UserRepository;
import com.backend.api.exceptions.DataIntegrityException;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.backend.api.pagination.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserServiceImpl implements UserService {
	private final BCryptPasswordEncoder pe;
	private final UserRepository userRepository;

	public List<UserProfile> getUserProfiles(Long id) {
		return userRepository.getUserProfiles(id);
	}

	public Page<UserProfile> getUserProfiles(Long id, Pageable pageable) {
		return userRepository.getUserProfiles(id, pageable);
	}

	@Override
	public User create(CreateUserDto createUserDto) {
		try {
			final User user = null;
			return this.userRepository.save(user);

		} catch (ConstraintViolationException e) {
			throw new DataIntegrityException(
					"Erro ao salvar registro no sistema, algum campo está preenchido incorretamente");
		}
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public User findById(Long id) {
		return null;
	}

	@Override
	public void update(Long id, Object o) {

	}

	@Override
	public Page<Object> findAll(Pageable pageable) {
		return null;
	}
}

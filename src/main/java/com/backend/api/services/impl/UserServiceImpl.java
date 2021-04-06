package com.backend.api.services.impl;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateUserDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.repositories.UserRepository;
import com.backend.api.exceptions.DataIntegrityException;
import com.backend.api.security.UserSS;
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
    private final DataMapper mapper;

    public List<UserProfile> getUserProfiles(Long id) {
        return userRepository.getUserProfiles(id);
    }

    public Page<UserProfile> getUserProfiles(Long id, Pageable pageable) {
        return userRepository.getUserProfiles(id, pageable.getPageable());
    }

    @Override
    public User getLoggedInUser() {
        UserSS user = UserSSServiceImpl.authenticated();
        return findById(user.getId());
    }

    @Override
    public User create(CreateUserDto createUserDto) {
        try {
            final User user = toEntity(createUserDto);
            return this.userRepository.save(user);

        } catch (ConstraintViolationException e) {
            throw new DataIntegrityException(
                    "Erro ao salvar registro no sistema, algum campo está preenchido incorretamente");
        }
    }

    @Override
    public User toEntity(CreateUserDto createUserDto) {
        return User
                .builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .password(pe.encode(createUserDto.getPassword()))
                .situation(UserSituation.ACTIVE)
                .build();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
    }

    @Override
    public void update(Long id, UpdateUserDto dto) {

    }

    @Override
    public Page<ReadUserDto> findAll(Pageable pageable) {
        final var users = userRepository.findAll(pageable.getPageable());
        return mapper.mapAllTo(users, ReadUserDto.class);
    }


}

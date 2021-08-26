package com.backend.api.services.impl;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.domain.enums.UserType;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateUserDto;
import com.backend.api.enums.Permission;
import com.backend.api.exceptions.DataIntegrityException;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Pageable;
import com.backend.api.query.UserQuery;
import com.backend.api.repositories.ProfileRepository;
import com.backend.api.repositories.UserRepository;
import com.backend.api.services.CompanyService;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserServiceImpl implements UserService {
  private final BCryptPasswordEncoder pe;
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;
  private final CompanyService companyService;
  private final UserQuery userQuery;
  private final DataMapper mapper;

  public List<UserProfile> findProfilesById(Long id) {
    return userRepository.getUserProfiles(id);
  }

  public Page<UserProfile> findProfilesById(Long id, Pageable pageable) {
    return userRepository.getUserProfiles(id, pageable.getPageable());
  }

  @Override
  public Page<ReadProfileDto> findUnlinkedProfiles(Long id, Pageable pageable, Specification specification) {
    return userQuery.findUnlinkedProfiles(id, pageable, specification);
    //        return userRepository.findUnlinkedProfiles(id, pageable.getPageable());
  }

  @Override
  public Page<ReadCompanyDto> findUnlinkedCompanies(Long id, Pageable pageable, Specification specification) {
    return userQuery.findUnlinkedCompanies(id, pageable, specification);
  }

  @Override
  public void addProfilesToUser(Long id, List<Long> ids) {
    final User user = findById(id);
    final List<UserProfile> profiles = profileRepository.findByIds(ids);
    profiles.forEach(profile -> profile.getUsers().add(user));
    profileRepository.saveAll(profiles);
  }

  @Override
  public void deleteProfile(Long id, Long profileId) {
    final User user = findById(id);
    final UserProfile profile = profileRepository.findById(profileId)
        .orElseThrow(() -> new ObjectNotFoundException("Perfil não "));
    profile.getUsers().remove(user);
    profileRepository.save(profile);
  }

  @Override
  public List<Permission> getUserMenus(User user) {
    Set<Permission> permissions = user.fetchAndFlattenPermissions();

    return permissions.stream().filter(
        permission -> permission.getType().equals("Menu")).collect(Collectors.toList()
    );
  }

  @Override
  public Page<Company> findCompaniesById(Long id, Pageable pageable) {
    return userRepository.getUserCompanies(id, pageable.getPageable());
  }

  @Override
  public User create(CreateUserDto createUserDto) {
    try {
      final User user = toEntity(createUserDto);
      this.userRepository.save(user);
      companyService.addEntity(user);
      return user;

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
        .type(UserType.ADMIN)
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
    final var i = id;
    throw new RuntimeException("Método não implementado");
  }

  @Override
  public Page<ReadUserDto> findAll(Pageable pageable) {
    final var users = userRepository.findAll(pageable.getPageable());
    return mapper.mapAllTo(users, ReadUserDto.class);
  }

  @Override
  public Page<User> findAll(Specification spec, PageRequest pageRequest) {
    return userRepository.findAll(spec, pageRequest);
  }


}

package com.backend.api.services.impl;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.dto.read.ReadPermissionDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.enums.Permission;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Pageable;
import com.backend.api.repositories.ProfileRepository;
import com.backend.api.repositories.RoleRepository;
import com.backend.api.repositories.UserRepository;
import com.backend.api.services.ProfileService;
import com.backend.api.services.RoleService;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileServiceImpl implements ProfileService {
  private final UserService userService;
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;
  private final RoleRepository roleRepository;
  private final RoleService roleService;
  private final DataMapper mapper;

  @Override
  public UserProfile create(CreateProfileDto createProfileDto) {
    validate(createProfileDto.getLevel());

    final var profile = toEntity(createProfileDto);
    profileRepository.save(profile);
    return profile;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public UserProfile findById(Long id) {
    return profileRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Perfil não encontrado"));
  }

  @Override
  public void update(Long id, UpdateProfileDto dto) {
    validate(dto.getLevel());
    final var profile = findById(id);
    profile.update(dto);
    profileRepository.save(profile);

  }

  @Override
  public Page<ReadProfileDto> findAll(Pageable pageable) {
    final var profiles = profileRepository.findAll(pageable.getPageable());
    return mapper.mapAllTo(profiles, ReadProfileDto.class);
  }

  @Override
  public Page<UserProfile> findAll(Specification spec, PageRequest pageRequest) {
    return profileRepository.findAll(spec, pageRequest);
  }

  @Override
  public UserProfile toEntity(CreateProfileDto createProfileDto) {

    return UserProfile
        .builder()
        .description(createProfileDto.getDescription())
        .level(createProfileDto.getLevel())
        .role(roleRepository.findByName("ADMIN"))
        .build();
  }

  @Override
  public Page<ReadPermissionDto> findLinkedPermissions(Long id, Pageable pageable) {
    UserProfile profile = findById(id);
    Set<Permission> permissions = profile.getRole().getPermissions();

    if (permissions.contains(Permission.ALL)) {
      List<ReadPermissionDto> singleDto = mapper.mapAllTo(List.of(Permission.ALL), ReadPermissionDto.class);
      return new PageImpl<ReadPermissionDto>(singleDto);
    }
    List<ReadPermissionDto> dtos = mapper.mapAllTo(permissions, ReadPermissionDto.class);
    return new PageImpl<ReadPermissionDto>(dtos);
  }

  @Override
  public Page<ReadPermissionDto> findUnlinkedPermissions(Long id, Pageable pageable) {
    UserProfile profile = findById(id);
    Set<Permission> permissions = profile.getRole().getPermissions();

    List<Permission> allPermissions = Arrays.stream(Permission.values()).collect(Collectors.toList());
    allPermissions.removeAll(permissions);
    List<ReadPermissionDto> dtoList = mapper.mapAllTo(allPermissions, ReadPermissionDto.class);
    return new PageImpl<ReadPermissionDto>(dtoList);
  }

  @Override
  public Page<User> findUnlinkedUsers(Long id, Pageable pageable) {
    return profileRepository.findUnlinkedUsers(id, pageable.getPageable());
  }

  @Override
  public List<UserProfile> findByIds(List<Long> ids) {
    return profileRepository.findByIds(ids);
  }

  @Override
  public void saveProfiles(List<UserProfile> profiles) {
    profileRepository.saveAll(profiles);
  }

  @Override
  public Page<User> findProfileUsers(Long id, Pageable pageable) {
    return profileRepository.findLinkedUsers(id, pageable.getPageable());
  }

  @Override
  public void savePermissions(Long id, List<Permission> permissions) {
    final UserProfile profile = findById(id);
    roleService.addPermissions(profile.getRole().getId(), permissions);
  }

  @Override
  public void deletePermission(Long id, Permission permission) {
    final UserProfile profile = findById(id);

    profile.getRole().getPermissions().remove(permission);
    profileRepository.save(profile);
  }

  @Override
  public void addUsers(Long id, List<Long> ids) {
    final UserProfile profile = findById(id);
    final Set<User> users = new HashSet<>(userRepository.findAllById(ids));
    profile.getUsers().addAll(users);
    profileRepository.save(profile);
  }

  @Override
  public void deleteUser(Long id, Long userId) {
    final UserProfile profile = findById(id);
    final User user = userService.findById(userId);
    profile.getUsers().remove(user);
    profileRepository.save(profile);
  }


  private void validate(Integer level) {
    User user = userService.getLoggedInUser();
    if (user.getMaxUserProfileLevel().getLevel() < level) {
      throw new RuntimeException("Não é possivel criar um perfil com nível maior que o seu.");
    }
  }
}

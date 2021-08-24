package com.backend.api.services;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateUserDto;
import com.backend.api.enums.Permission;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

public interface UserService extends CrudService<User, CreateUserDto, ReadUserDto, UpdateUserDto, Filter> {
  List<UserProfile> findProfilesById(Long id);

  Page<UserProfile> findProfilesById(Long id, Pageable pageable);

  User getLoggedInUser();

  Page<ReadProfileDto> findUnlinkedProfiles(Long id, Pageable pageable, Specification specification);

  void addProfilesToUser(Long id, List<Long> ids);

  void deleteProfile(Long id, Long profileId);

  List<Permission> getUserMenus(User user);
}

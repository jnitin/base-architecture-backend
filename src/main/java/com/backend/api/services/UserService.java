package com.backend.api.services;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateUserDto;
import com.backend.api.enums.Permission;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface UserService extends CrudService<User, CreateUserDto, ReadUserDto, UpdateUserDto, Filter> {
  List<UserProfile> findProfilesById(Long id);

  Page<UserProfile> findProfilesById(Long id, Pageable pageable);

  static User getLoggedInUser() {
    return ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication())
        .getUser();
  }

  ;

  Page<ReadProfileDto> findUnlinkedProfiles(Long id, Pageable pageable, Specification specification);

  Page<ReadCompanyDto> findUnlinkedCompanies(Long id, Pageable pageable, Specification specification);

  void addProfilesToUser(Long id, List<Long> ids);

  void deleteProfile(Long id, Long profileId);

  List<Permission> getUserMenus(User user);

  Page<Company> findCompaniesById(Long id, Pageable pageable);
}

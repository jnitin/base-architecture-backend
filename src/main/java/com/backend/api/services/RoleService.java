package com.backend.api.services;

import com.backend.api.domain.Role;
import com.backend.api.enums.Permission;

import java.util.List;

public interface RoleService {
  void addPermissions(Long id, List<Permission> permissions);
  Role findById(Long id);
}

package com.backend.api.services;

import com.backend.api.domain.User;
import com.backend.api.enums.Permission;

import java.util.List;

public interface PermissionService {
  List<Permission> findAll(User user);
}

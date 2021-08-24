package com.backend.api.services.impl;

import com.backend.api.domain.User;
import com.backend.api.enums.Permission;
import com.backend.api.services.PermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
  @Override
  public List<Permission> findAll(User user) {
    Set<Permission> permissions = user.fetchAndFlattenPermissions();
    if (permissions.contains(Permission.ALL)) {
      return List.of(Permission.ALL);
    }
    return new ArrayList<>(permissions);
  }
}

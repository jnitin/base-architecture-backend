package com.backend.api.services.impl;

import com.backend.api.domain.Role;
import com.backend.api.enums.Permission;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.RoleRepository;
import com.backend.api.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public void addPermissions(Long id, List<Permission> permissions) {
    Role role = findById(id);
    role.getPermissions().addAll(permissions);
    roleRepository.save(role);
  }

  @Override
  public Role findById(Long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Role não encontrado"));
  }


}

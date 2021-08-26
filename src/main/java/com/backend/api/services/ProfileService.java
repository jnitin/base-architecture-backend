package com.backend.api.services;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.dto.read.ReadPermissionDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.enums.Permission;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfileService extends CrudService<UserProfile, CreateProfileDto, ReadProfileDto, UpdateProfileDto, Filter> {
    Page<ReadPermissionDto> findLinkedPermissions(Long id, Pageable pageable);

    Page<ReadPermissionDto> findUnlinkedPermissions(Long id, Pageable pageable);

    Page<User> findUnlinkedUsers(Long id, Pageable pageable);

    Page<User> findProfileUsers(Long id, Pageable pageable);

    void savePermissions(Long id, List<Permission> permissions);

    void deletePermission(Long id, Permission permission);

    void addUsers(Long id, List<Long> ids);

    void deleteUser(Long id, Long userId);
}

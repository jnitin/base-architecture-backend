package com.backend.api.controllers;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.read.ReadPermissionDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.enums.Permission;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.query.LinkedQuery;
import com.backend.api.query.impl.LinkedQueryImpl;
import com.backend.api.services.ProfileService;
import com.backend.api.services.impl.CrudLinkerServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController extends CrudController<UserProfile, CreateProfileDto, ReadProfileDto, UpdateProfileDto, Filter> {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        super(profileService);
        this.profileService = profileService;
    }

    @GetMapping("/{id}/permissions")
    public Page<ReadPermissionDto> getPermissions(@PathVariable Long id, Pageable pageable, UserAuthentication userAuthentication) throws Exception {
        return mapper.mapAllTo(profileService.findLinkedPermissions(id, pageable), ReadPermissionDto.class);
    }

    @PostMapping(value = "/{id}/permissions")
    public void addPermissions(@PathVariable Long id, @RequestBody List<Permission> permissions) {
        profileService.savePermissions(id, permissions);
    }

    @DeleteMapping(value = "/{id}/permissions/{permission}")
    public void deleteRoute(@PathVariable Long id, @PathVariable Permission permission) {
        profileService.deletePermission(id, permission);
    }

    @GetMapping(value = "/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ReadUserDto>> getUsers(@PathVariable Long id, Pageable pageable) {
        Page<User> users = profileService.findProfileUsers(id, pageable);
        Page<ReadUserDto> page = mapper.mapAllTo(users, ReadUserDto.class);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping(value = "/{id}/users")
    public void addUsers(@PathVariable Long id, @RequestBody List<Long> ids) {
        profileService.addUsers(id, ids);
    }

    @DeleteMapping(value = "/{id}/users/{userId}")
    public void deleteUser(@PathVariable Long id, @PathVariable Long userId) {
        profileService.deleteUser(id, userId);
    }

    @GetMapping(value = "/{id}/unlinked-permissions")
    public Page<ReadPermissionDto> findProfileUnlinkedPermissions(@PathVariable Long id, Pageable pageable) {
        return profileService.findUnlinkedPermissions(id, pageable);
    }

    @GetMapping(value = "/{id}/unlinked-users")
    public Page<ReadUserDto> findProfileUnlinkedUsers(@PathVariable Long id, Pageable pageable) {
        return mapper.mapAllTo(profileService.findUnlinkedUsers(id, pageable), ReadUserDto.class);
    }

    @RestController
    @RequestMapping(value = "/profiles/{id}/companies")
    static class CompaniesLink extends CrudLinkerController<UserProfile, Company, ReadCompanyDto> {
        public CompaniesLink(CrudLinkerServiceImpl<UserProfile, Company, ReadCompanyDto> service, DataMapper mapper, EntityManager entityManager) {
            super(service, mapper);
            LinkedQuery<UserProfile, Company, ReadCompanyDto> query = new LinkedQueryImpl<>(entityManager, UserProfile.class, Company.class, ReadCompanyDto.class);
            service.setQuery(query);
        }
    }
}
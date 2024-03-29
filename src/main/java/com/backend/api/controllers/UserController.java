package com.backend.api.controllers;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.User;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.dto.read.ReadCompanyDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateUserDto;
import com.backend.api.enums.Permission;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.services.ProfileService;
import com.backend.api.services.UserService;
import com.backend.api.utils.Basics;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends CrudController<User, CreateUserDto, ReadUserDto, UpdateUserDto, Filter> {

  private final ProfileService profileService;

  private final UserService userService;

  public UserController(ProfileService profileService, UserService userService) {
    super(userService);
    this.profileService = profileService;
    this.userService = userService;
  }

  @GetMapping("/menus")
  public ResponseEntity<List<Permission>> getUserMenus(UserAuthentication userAuthentication) {
    return ResponseEntity.ok().body(userService.getUserMenus(userAuthentication.getUser()));
  }


  @GetMapping(value = "/{id}/profiles")
  public ResponseEntity<Page<ReadProfileDto>> getUserProfiles(@PathVariable Long id, Pageable pageable) {
    Page<ReadProfileDto> profiles = mapper.mapAllTo(userService.findProfilesById(id, pageable), ReadProfileDto.class);
    return ResponseEntity.ok().body(profiles);
  }

  @PostMapping(value = "/{id}/profiles")
  public void addProfilesToUser(@PathVariable Long id, @RequestBody List<Long> ids) {
    userService.addProfilesToUser(id, ids);
  }

  @DeleteMapping(value = "/{id}/profiles/{profileId}")
  public void deleteProfile(@PathVariable Long id, @PathVariable Long profileId) {
    userService.deleteProfile(id, profileId);
  }

  @GetMapping(value = "/{id}/unlinked-profiles")
  public Page<ReadProfileDto> findUnlinkedProfiles(@PathVariable Long id, Pageable pageable, Filter filter, UserAuthentication userAuthentication) {
    final Specification specification = Basics.generateSpecification(filter, userAuthentication.getCompany(), userAuthentication.getUser());
    return userService.findUnlinkedProfiles(id, pageable, specification);
  }

  @GetMapping(value = "/{id}/unlinked-companies")
  public Page<ReadCompanyDto> findUnlinkedCompanies(@PathVariable Long id, Pageable pageable, Filter filter, UserAuthentication userAuthentication) {
    final Specification specification = Basics.generateSpecification(filter, userAuthentication.getCompany(), userAuthentication.getUser());
    return userService.findUnlinkedCompanies(id, pageable, specification);
  }

  @GetMapping(value = "/{id}/companies")
  public ResponseEntity<Page<ReadCompanyDto>> getUserCompanies(@PathVariable Long id, Pageable pageable) {
    Page<ReadCompanyDto> profiles = mapper.mapAllTo(userService.findCompaniesById(id, pageable), ReadCompanyDto.class);
    return ResponseEntity.ok().body(profiles);
  }

}
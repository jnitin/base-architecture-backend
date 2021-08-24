package com.backend.api.controllers;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.dto.read.ReadPermissionDto;
import com.backend.api.mapper.DataMapper;
import com.backend.api.services.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/permissions")
@AllArgsConstructor
public class PermissionController {
  private final DataMapper mapper;

  private final PermissionService permissionService;

  @GetMapping
  public List<ReadPermissionDto> listAll(UserAuthentication userAuthentication) {
    return mapper.mapAllTo(permissionService.findAll(userAuthentication.getUser()), ReadPermissionDto.class);
  }

}
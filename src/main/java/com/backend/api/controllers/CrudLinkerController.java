package com.backend.api.controllers;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.services.impl.CrudLinkerServiceImpl;
import com.backend.api.utils.Basics;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;


public class CrudLinkerController<A, B, BDto> {

  private final CrudLinkerServiceImpl<A, B, BDto> service;
  private final DataMapper mapper;

  public CrudLinkerController(CrudLinkerServiceImpl<A, B, BDto> service, DataMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  private Class getDtoClass() {
    return (Class<BDto>) ((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[2];
  }


  @GetMapping
  ResponseEntity<Page<BDto>> getBList(@PathVariable Long id, Pageable pageable, Filter filter, UserAuthentication userAuthentication) {
    Specification spec = Basics.generateSpecification(filter, userAuthentication.getCompany(), userAuthentication.getUser());
    Page page = mapper.mapAllTo(service.findAllBEntitiesById(id, pageable, spec), getDtoClass());
    return ResponseEntity.ok().body(page);
  }

  @GetMapping(value = "/unlinked")
  Page findUnlinkedB(@PathVariable Long id, Pageable pageable, Filter filter, UserAuthentication userAuthentication) {
    final Specification specification = Basics.generateSpecification(filter, userAuthentication.getCompany(), userAuthentication.getUser());
    return mapper.mapAllTo(service.findUnlinkedBEntities(id, pageable, specification), getDtoClass());
  }

  @PostMapping
  void addBEntityToAEntity(@PathVariable Long id, @RequestBody List<Long> ids) {
    service.addBEntityToAEntity(id, ids);
  }

  @DeleteMapping(value = "/{id}/")
  void deleteLinkedBEntity(@PathVariable Long id, @PathVariable Long profileId) {
    service.deleteLinkedBEntity(id, profileId);
  }


}

package com.backend.api.controllers;

import com.backend.api.domain.Base;
import com.backend.api.mapper.DataMapper;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import com.backend.api.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;

public class CrudController<Bean extends Base, CreateDto, ReadDto, UpdateDto, FilterDto extends Filter> {

    protected final CrudService<Bean, CreateDto, ReadDto, UpdateDto, FilterDto> service;
    @Autowired
    protected DataMapper mapper;

    private final Class<ReadDto> readDtoClass;

    public CrudController(CrudService service) {
        this.readDtoClass = (Class<ReadDto>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[2];
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReadDto> find(@PathVariable Long id) {
        Bean bean = service.findById(id);
        ReadDto dto = mapper.mapTo(bean, readDtoClass);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ReadDto>> listAll(Pageable pageable, FilterDto filter) {
        Page<ReadDto> listDto = service.findAll(pageable, filter);
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> insert(@Valid @RequestBody CreateDto createDto) {
        final var entity = service.create(createDto);
        return ResponseEntity.created(createLocation(entity.getId()).toUri()).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @Valid @RequestBody UpdateDto updateDto) {
        service.update(id, updateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    protected UriComponents createLocation(Long id) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        return builder.path("/").path(String.valueOf(id)).build();
    }


}
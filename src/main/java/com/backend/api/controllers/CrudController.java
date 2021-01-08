package com.backend.api.controllers;

import com.backend.api.domain.Base;
import com.backend.api.pagination.Searchable;
import com.backend.api.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class CrudController<Bean extends Base, DTO> {

    @Autowired
    protected CrudService<Bean, DTO> service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<DTO> find(@PathVariable Integer id) {
        DTO obj = service.toDTO(service.find(id));
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "findall", method = RequestMethod.GET)
    public ResponseEntity<List<DTO>> findAll() {
        List<Bean> list = service.findAll();
        List<DTO> listDto = list.stream().map(obj -> service.toDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody DTO objDto) {
        System.out.println("Ah não");
        Bean obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody DTO objDTO, @PathVariable Integer id) {
        Bean obj = service.fromDTO(objDTO);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<DTO>> search(Searchable searchable) {
        Page<DTO> listDto = service.findPage(searchable);
        return ResponseEntity.ok().body(listDto);
    }

}
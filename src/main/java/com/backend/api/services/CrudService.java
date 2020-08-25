package com.backend.api.services;

import java.util.List;

import com.backend.api.domain.Base;
import com.backend.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<Bean extends Base, DTO> {

    @Autowired
    protected JpaRepository<Bean, Integer> repo;

    public abstract Bean fromDTO(DTO obj);

    public abstract DTO toDTO(Bean obj);

    public Bean update(Bean obj) {
        Bean newObj = find(obj.getId());
        newObj.clone(obj);
        return repo.save(newObj);
    }

    public Bean find(Integer id) {
        Bean obj = this.repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Item não encontrado."));
        return obj;
    }

    public Bean insert(Bean obj) {
        obj.setId(null);
        return this.repo.save(obj);
    }

    public void delete(Integer id) {
        this.repo.deleteById(id);

    }

    public Page<Bean> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
    }

    public List<Bean> findAll() {
        return repo.findAll();
    }

}
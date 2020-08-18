package com.backend.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<Bean, DTO> {

    @Autowired
    protected JpaRepository<Bean, Integer> repo;

    public abstract Bean fromDTO(DTO obj);

    public abstract DTO toDTO(Bean obj);

    public abstract Bean insert(Bean obj);

    public abstract Bean update(Bean obj);

    public abstract void delete(Integer id);

    public abstract Page<Bean> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

    public Bean find(Integer id) {
        return this.repo.findById(id).orElse(null);
    }

    public abstract List<Bean> findAll();

}
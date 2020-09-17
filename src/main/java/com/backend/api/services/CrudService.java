package com.backend.api.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.backend.api.domain.Base;
import com.backend.api.services.exceptions.ObjectNotFoundException;
import com.backend.api.utils.CrudSpecificationBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public abstract class CrudService<Bean extends Base, DTO> {

    @Autowired
    public JpaRepository<Bean, Integer> repo;

    @Autowired
    public JpaSpecificationExecutor<Bean> executor;

    @Autowired
    protected HttpServletRequest request;

    public abstract Bean fromDTO(DTO obj);

    public abstract DTO toDTO(Bean obj);

    protected Class<DTO> dtoClass;

    protected Class<Bean> beanClass;

    public CrudService(final Class<Bean> beanClass, final Class<DTO> dtoClass) {
        this.dtoClass = dtoClass;
        this.beanClass = beanClass;
    }

    public Bean update(final Bean obj) {
        final Bean newObj = find(obj.getId());
        newObj.clone(obj);
        return repo.save(newObj);
    }

    public Bean find(final Integer id) {
        final Bean obj = this.repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Item não encontrado."));
        return obj;
    }

    public Bean insert(final Bean obj) {
        obj.setId(null);
        return this.repo.save(obj);
    }

    public void delete(final Integer id) {
        this.repo.deleteById(id);

    }

    public Page<DTO> findPage(final Integer page, final Integer linesPerPage, final String orderBy,
            final String direction, final String search) {
        CrudSpecificationBuilder<Bean> builder = new CrudSpecificationBuilder<>();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|=|%)(\\w+?)(,|\\|)");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }
        final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Specification<Bean> spec = builder.build();
        Page<Bean> list = executor.findAll(spec, pageRequest);

        Page<DTO> listDto = list.map(c -> toDTO(c));
        return listDto;
    }

    public List<Bean> findAll() {
        return repo.findAll();
    }

}
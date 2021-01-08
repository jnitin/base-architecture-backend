package com.backend.api.services;

import com.backend.api.domain.Base;
import com.backend.api.pagination.Searchable;
import com.backend.api.services.exceptions.DataIntegrityException;
import com.backend.api.services.exceptions.ObjectNotFoundException;
import com.backend.api.utils.CrudSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
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

    public List<DTO> toDTO(List<Bean> beans) {
        return beans.stream().map(this::toDTO).collect(Collectors.toList());
    }

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
        return this.repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Item não encontrado."));
    }

    public Bean insert(final Bean obj) {
        obj.setId(null);
        try {
            return this.repo.save(obj);

        } catch (ConstraintViolationException e) {
            throw new DataIntegrityException(
                    "Erro ao salvar registro no sistema, algum campo está preenchido incorretamente");
        }
    }

    public void delete(final Integer id) {
        this.repo.deleteById(id);

    }

    private Page<DTO> findPage(final Searchable searchable, CrudSpecificationBuilder<Bean> builder) {
        final String search = searchable.getSearch();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|=|%)(\\w+?)(,|\\|)");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }

        Specification<Bean> spec = builder.build();
        Page<Bean> list = executor.findAll(spec, searchable.getPageable());

        return list.map(this::toDTO);
    }

    public Page<DTO> findPage(final Searchable searchable, List<Bean> notIn, String key) {
        CrudSpecificationBuilder<Bean> builder = new CrudSpecificationBuilder<>();
        builder.with(notIn, key);
        return findPage(searchable, builder);
    }

    public Page<DTO> findPage(final Searchable searchable) {
        CrudSpecificationBuilder<Bean> builder = new CrudSpecificationBuilder<>();
        return findPage(searchable, builder);
    }

    public List<Bean> findAll() {
        return repo.findAll();
    }

}
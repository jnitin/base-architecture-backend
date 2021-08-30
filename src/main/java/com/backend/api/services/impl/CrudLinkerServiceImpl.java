package com.backend.api.services.impl;

import com.backend.api.pagination.Pageable;
import com.backend.api.query.LinkerQuery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@Getter
@Setter
public class CrudLinkerServiceImpl<A, B, BDto> {
  private LinkerQuery<A, B, BDto> query;

  public Page<?> findAllBEntitiesById(Long id, Pageable pageable, Specification spec) {
    return query.findAllBEntitiesById(id, pageable, spec);
  }

  private Class<B> getBClass() {
    return (Class<B>) ((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[1];
  }

  public void addBEntityToAEntity(Long id, List<Long> ids) {
    // query.addBEntityToAEntity(id, ids);
  }

  public void deleteLinkedBEntity(Long id, Long profileId) {
    //query.deleteLinkedBEntity(id, profileId);
  }

  public Page<B> findUnlinkedBEntities(Long id, Pageable pageable, Specification specification) {
    return query.findUnlinkedBEntities(id, pageable, specification);
  }
}

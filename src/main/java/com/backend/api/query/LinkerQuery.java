package com.backend.api.query;

import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LinkerQuery<A, B, BDto> {
  Page<B> findAllBEntitiesById(Long id, Pageable pageable, Specification specification);

  void addBEntityToAEntity(Long id, List<Long> ids);

  void deleteLinkedBEntity(Long id, Long profileId);

  Page<B> findUnlinkedBEntities(Long id, Pageable pageable, Specification specification);

}

package com.backend.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

public class CrudSpecificationBuilder<Bean> {

  private final List<SearchCriteria> params;

  public CrudSpecificationBuilder() {
    params = new ArrayList<SearchCriteria>();
  }

  public CrudSpecificationBuilder<Bean> with(String key, String operation, Object value, String connector) {
    params.add(new SearchCriteria(key, operation, value, connector));
    return this;
  }

  public Specification<Bean> build() {
    if (params.size() == 0) {
      return null;
    }

    List<CrudSpecification<Bean>> specs = params.stream().map(CrudSpecification<Bean>::new)
        .collect(Collectors.toList());

    Specification<Bean> result = specs.get(0);

    for (int i = 1; i < params.size(); i++) {
      result = params.get(i-1).isOrPredicate() ? Specification.where(result).or(specs.get(i))
          : Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}
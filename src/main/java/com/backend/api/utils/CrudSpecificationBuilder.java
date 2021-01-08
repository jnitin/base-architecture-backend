package com.backend.api.utils;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrudSpecificationBuilder<Bean> {

  private final List<SearchCriteria> params;

  public CrudSpecificationBuilder() {
    params = new ArrayList<SearchCriteria>();
  }

  public CrudSpecificationBuilder<Bean> with(String key, String operation, Object value, String connector) {
    params.add(new SearchCriteria(key, operation, value, connector));
    return this;
  }

  public CrudSpecificationBuilder<Bean> with(List<Bean> notIn, String key) {
    params.add(new SearchCriteria(notIn, key));
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
package com.backend.api.utils;

import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrudSpecificationBuilder<Bean> {

  private final List<SearchCriteria> params;
  private final Company company;
  private final User user;

  public CrudSpecificationBuilder(Company company, User user) {
    this.user = user;
    params = new ArrayList<SearchCriteria>();
    this.company = company;
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
      return new CrudSpecification<Bean>(null, company, user );
    }

    List<CrudSpecification<Bean>> specs = params.stream().map(spec -> new CrudSpecification<Bean> (spec, company, user))
        .collect(Collectors.toList());

    Specification<Bean> result = specs.get(0);

    for (int i = 1; i < params.size(); i++) {
      result = params.get(i-1).isOrPredicate() ? Specification.where(result).or(specs.get(i))
          : Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}
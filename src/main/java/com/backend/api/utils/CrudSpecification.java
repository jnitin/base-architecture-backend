package com.backend.api.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.backend.api.services.exceptions.DataIntegrityException;

import org.springframework.data.jpa.domain.Specification;

public class CrudSpecification<Bean> implements Specification<Bean> {

  private static final long serialVersionUID = 1L;
  private SearchCriteria criteria;

  public CrudSpecification(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  public CrudSpecification() {
  }

  @Override
  public Predicate toPredicate(Root<Bean> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    try {
      root.getJavaType().getDeclaredField(criteria.getKey());
    } catch (NoSuchFieldException e) {
      throw new DataIntegrityException("Campo (" + criteria.getKey() + ") do filtro não encontrado");
    }
    final String key = criteria.getKey();
    final String value = criteria.getValue().toString();
    switch (criteria.getOperation()) {
      case ">":
        return builder.greaterThanOrEqualTo(root.<String>get(key), value);
      case "<":
        return builder.lessThanOrEqualTo(root.<String>get(key), value);
      case ":": // Contains
        if (root.get(key).getJavaType() == String.class) {
          return builder.like(builder.upper(root.<String>get(key)),
              "%" + value.toUpperCase() + "%");
        } else {
          return builder.equal(root.get(key), criteria.getValue());
        }
      case "=": // Exact
        if (root.get(key).getJavaType() == String.class) {
          return builder.equal(root.<String>get(key), value);
        } else {
          return builder.equal(root.get(key), criteria.getValue());
        }
      case "%": // Starts With
        if (root.get(key).getJavaType() == String.class) {
          return builder.like(builder.upper(root.<String>get(key)),
              value.toUpperCase() + "%");
        }
    }
    return null;
  }

}
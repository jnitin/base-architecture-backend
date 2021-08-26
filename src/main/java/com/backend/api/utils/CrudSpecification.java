package com.backend.api.utils;

import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.exceptions.DataIntegrityException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class CrudSpecification<Bean> implements Specification<Bean> {
  private final SearchCriteria criteria;
  private final Company company;
  private final User user;

  @Override
  public Predicate toPredicate(Root<Bean> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

    try {
      root.getJavaType().getDeclaredField(criteria.getKey());
    } catch (NoSuchFieldException e) {
      throw new DataIntegrityException("Campo (" + criteria.getKey() + ") do filtro não encontrado");
    } catch (NullPointerException ignored) {

    }
    final var entityName = root.getModel().getName();

    Predicate linkCompany = null;
    cq.groupBy(root.get("id"));


    if (!entityName.equals("Company")) {
      final Join<Company, Bean> companyBeanJoin = root.join("companies", JoinType.LEFT);


      if (company == null) {
        linkCompany = cb.conjunction();
        for (Company c : user.getCompanies()) {
          linkCompany = cb.or(linkCompany, cb.literal(c).in(companyBeanJoin));
        }

      } else if (company.getId() == null) {
        linkCompany = cb.isTrue(cb.literal(true));
      } else {
        linkCompany = cb.isTrue(cb.literal(company).in(companyBeanJoin));
      }
    } else {
      linkCompany = cb.isTrue(cb.literal(true));
    }
    if (criteria == null) {
      return linkCompany;
    }

    final String key = criteria.getKey();
    // Caso haja uma lista de ids no criteria em notIn, vai retornar um query
    // filtrada sem aqueles ids
    if (criteria.getNotIn() != null) {
      Predicate predicate = root.in(criteria.getNotIn()).not();
      return cb.and(predicate, linkCompany);
    }

    final String value = criteria.getValue().toString();

    Predicate ret = cb.conjunction();

    switch (criteria.getOperation()) {
      case ">":
        ret.getExpressions().add(cb.and(cb.greaterThanOrEqualTo(root.get(key), value), linkCompany));
      case "<":
        ret.getExpressions().add(cb.and(cb.lessThanOrEqualTo(root.get(key), value), linkCompany));
      case ":": // Contains
        if (root.get(key).getJavaType() == String.class) {
          ret.getExpressions().add(cb.and(cb.like(cb.upper(root.get(key)), "%" + value.toUpperCase() + "%"), linkCompany));
        } else {
          ret.getExpressions().add(cb.and(cb.equal(root.get(key), criteria.getValue()), linkCompany));
        }
      case "=": // Exact
        if (root.get(key).getJavaType() == String.class) {
          ret.getExpressions().add(cb.and(cb.equal(root.<String>get(key), value), linkCompany));
        } else {
          ret.getExpressions().add(cb.and(cb.equal(root.get(key), criteria.getValue()), linkCompany));
        }
      case "%": // Starts With
        if (root.get(key).getJavaType() == String.class) {
          ret.getExpressions().add(cb.and(cb.like(cb.upper(root.get(key)), value.toUpperCase() + "%"), linkCompany));
        }
    }

    return ret;
  }

}
package com.backend.api.query.impl;

import com.backend.api.pagination.Pageable;
import com.backend.api.query.LinkerQuery;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Setter
public class LinkerQueryImpl<A, B, BDto> implements LinkerQuery<A, B, BDto> {

  private final EntityManager entityManager;
  private final Class<A> aClass;
  private final Class<B> bClass;
  private final Class<BDto> bDtoClass;

  private String joinField;

  public LinkerQueryImpl(EntityManager entityManager, Class<A> aClass, Class<B> bClass, Class<BDto> bDtoClass) {
    this.entityManager = entityManager;
    this.aClass = aClass;
    this.bClass = bClass;
    this.bDtoClass = bDtoClass;
  }

  @Override
  public Page<B> findAllBEntitiesById(Long id, Pageable pageable, Specification spec) {
    if (joinField == null) {
      throw new RuntimeException("Join field not set");
    }

    final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    final CriteriaQuery<B> cq = cb.createQuery(bClass);

    final Root<A> root = cq.from(aClass);
    final Join<A, B> bJoin = root.join(joinField, JoinType.LEFT);

    cq.select(bJoin);

    final Predicate specAux = spec != null ?
        spec.toPredicate(root, cq, cb) :
        cb.isTrue(cb.literal(true));

    cq.where(
        cb.and(
            specAux,
            cb.or(
                cb.equal(root.get("id"), cb.literal(id))
            )
        )
    );

    cq.groupBy(root.get("id"), bJoin.get("id"));
    cq.orderBy(QueryUtils.toOrders(pageable.getPageable().getSort(), root, cb));
    final TypedQuery<B> typedQuery = entityManager.createQuery(cq);

    final List<B> list = typedQuery.getResultList();
    return new PageImpl<B>(list, pageable.getPageable(), 10);
  }

  @Override
  public void deleteLinkedBEntity(Long id, Long profileId) {

  }

  @Override
  public Page findUnlinkedBEntities(Long id, Pageable pageable, Specification specification) {
    return null;
  }

  @Override
  public void addBEntityToAEntity(Long id, List ids) {

  }
}

package com.backend.api.query.impl;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.pagination.Pageable;
import com.backend.api.query.UserQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserQueryImpl implements UserQuery {
  private final EntityManager entityManager;

  @Override
  public Page<ReadProfileDto> findUnlinkedProfiles(Long id, Pageable pageable, Specification specification) {
    final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    final CriteriaQuery<ReadProfileDto> cq = cb.createQuery(ReadProfileDto.class);

    final Root<UserProfile> root = cq.from(UserProfile.class);
    final Join<UserProfile, User> userJoin = root.join("users", JoinType.LEFT);

    cq.select(
        cb.construct(ReadProfileDto.class,
            root.get("id"),
            root.get("description"),
            root.get("level")
        )
    );

final Predicate specAux = specification != null ?
    specification.toPredicate(root, cq, cb) :
    cb.isTrue(cb.literal(true));

    cq.where(
        cb.and(
            specAux,
            cb.or(
                cb.not(
                    cb.equal(
                        userJoin.get("id"),
                        cb.literal(id)
                    )
                ),
                cb.isNull(userJoin.get("id"))
            )
        )
    );

    cq.groupBy(root.get("id"));
    cq.orderBy(QueryUtils.toOrders(pageable.getPageable().getSort(), root, cb));
    final TypedQuery<ReadProfileDto> typedQuery = entityManager.createQuery(cq);

    final List<ReadProfileDto> list = typedQuery.getResultList();
    return new PageImpl<ReadProfileDto>(list, pageable.getPageable(), 10);
  }
}

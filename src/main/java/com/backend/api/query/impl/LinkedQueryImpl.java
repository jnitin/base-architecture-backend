package com.backend.api.query.impl;

import com.backend.api.pagination.Pageable;
import com.backend.api.query.LinkedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class LinkedQueryImpl<A, B, BDto> implements LinkedQuery<A, B, BDto> {

    private final EntityManager entityManager;
    private final Class<A> aClass;
    private final Class<B> bClass;
    private final Class<BDto> bDtoClass;

    private CriteriaBuilder cb;
    private CriteriaQuery cq;
    private Root<A> root;
    private Join<A, B> bJoin;
    private Predicate pred;

    public LinkedQueryImpl(EntityManager entityManager, Class<A> aClass, Class<B> bClass, Class<BDto> bDtoClass) {
        this.entityManager = entityManager;
        this.aClass = aClass;
        this.bClass = bClass;
        this.bDtoClass = bDtoClass;
    }

    @Override
    public Page<B> findAllBEntitiesById(Long id, Pageable pageable, Specification spec) {
        init(spec);

        where(
                cb.equal(root.get("id"), cb.literal(id))
        );

        return postWhere(pageable);
    }

    private void where(Predicate where) {
        cq.where(
                cb.and(
                        this.pred,
                        cb.or(where)
                )
        );
    }


    @Override
    public void deleteLinkedBEntity(Long id, Long profileId) {

    }

    @Override
    public Page findUnlinkedBEntities(Long id, Pageable pageable, Specification spec) {
        cb = entityManager.getCriteriaBuilder();
        cq = cb.createQuery(aClass);

        root = cq.from(bClass);
        String joinField = getJoinField(bClass, aClass);
        bJoin = root.join(joinField, JoinType.LEFT);

        pred = spec != null ?
                spec.toPredicate(root, cq, cb) :
                cb.isTrue(cb.literal(true));

        cq.select(bJoin);


        where(
                cb.isNull(bJoin.get("id"))
        );

        return postWhere(pageable);
    }

    @Override
    public void addBEntityToAEntity(Long id, List ids) {

    }

    private void init(Specification spec) {
        cb = entityManager.getCriteriaBuilder();
        cq = cb.createQuery(bClass);

        root = cq.from(aClass);

        String joinField = getJoinField(aClass, bClass);
        bJoin = root.join(joinField, JoinType.LEFT);

        pred = spec != null ?
                spec.toPredicate(root, cq, cb) :
                cb.isTrue(cb.literal(true));
        cq.select(bJoin);

    }

    private Page<B> postWhere(Pageable pageable) {
        cq.groupBy(root.get("id"), bJoin.get("id"));
        cq.orderBy(QueryUtils.toOrders(pageable.getPageable().getSort(), root, cb));
        final TypedQuery<B> typedQuery = entityManager.createQuery(cq);

        final List<B> list = typedQuery.getResultList();
        return new PageImpl(list, pageable.getPageable(), 10);
    }

    private String getJoinField(Class a, Class b) {
        for (Field f : a.getDeclaredFields()) {
            Boolean pred1 = f.getType().equals(Set.class);
            Boolean pred2 = f.getGenericType().getTypeName().contains("<" + b.getName() + ">");
            if (pred1 && pred2) {
                return f.getName();
            }
        }
        return null;
    }
}

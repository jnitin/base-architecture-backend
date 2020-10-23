package com.backend.api.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.backend.api.domain.Base;
import com.backend.api.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class LinkableService<A extends Base, B extends Base> {

  public JpaRepository<A, Integer> repoA;

  public JpaRepository<B, Integer> repoB;

  public CrudService serviceA;

  public CrudService serviceB;

  public LinkableService(CrudService serviceA, CrudService serviceB) {
    this.repoA = serviceA.repo;
    this.repoB = serviceB.repo;

    this.serviceA = serviceA;
    this.serviceB = serviceB;
  }

  public void insert(Integer id, List<Integer> ids, String getterA, String getterB, String erro)
      throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    A a = repoA.findById(id).orElseThrow(() -> new ObjectNotFoundException(erro));
    Set<B> setB = repoB.findAllById(ids).stream().collect(Collectors.toSet());
    Method m = a.getClass().getMethod(getterA);
    ((Set<B>) m.invoke(a)).addAll(setB);

    for (B b : setB) {
      ((Set<A>) b.getClass().getMethod(getterB).invoke(b)).add(a);
    }

    repoA.save(a);
  }

  public void delete(Integer id, Integer id2, String getterA, String getterB, String erro1, String erro2)
      throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    A a = repoA.findById(id).orElseThrow(() -> new ObjectNotFoundException(erro1));
    B b = repoB.findById(id2).orElseThrow(() -> new ObjectNotFoundException(erro2));

    ((Set<B>) a.getClass().getMethod(getterA).invoke(a)).remove(b);
    ((Set<A>) b.getClass().getMethod(getterB).invoke(b)).remove(a);
    repoA.save(a);
  }

  public Page<B> getLinkedRecords(Integer id, String repositoryGetter, Integer page, Integer linesPerPage,
      String orderBy, String direction) throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    Method m = serviceA.getClass().getMethod(repositoryGetter, Integer.class, PageRequest.class);
    return (Page<B>) m.invoke(serviceA, id, pageRequest);
  }
}
package com.backend.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>, JpaSpecificationExecutor<Route> {
  @Transactional()
  @Query("SELECT p from Route r JOIN r.profiles p WHERE r.id = :routeId")
  public Page<UserProfile> getProfiles(@Param("routeId") Integer routeId, Pageable pageable);

  @Transactional()
  @Query("SELECT p from Route r JOIN r.profiles p WHERE r.id = :routeId")
  public List<UserProfile> getProfiles(@Param("routeId") Integer id);

}

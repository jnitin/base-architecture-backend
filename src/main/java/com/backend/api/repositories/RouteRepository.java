package com.backend.api.repositories;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import org.springframework.data.domain.Page;
import com.backend.api.pagination.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
  @Transactional()
  @Query("SELECT x from Route r JOIN r.profiles x WHERE r.id = :routeId")
  Page<UserProfile> getProfiles(@Param("routeId") Long routeId, Pageable pageable);

  @Transactional()
  @Query("SELECT x from Route r JOIN r.profiles x WHERE r.id = :routeId")
  List<UserProfile> getProfiles(@Param("routeId") Long id);

}

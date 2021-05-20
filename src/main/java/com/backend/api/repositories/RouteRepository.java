package com.backend.api.repositories;

import com.backend.api.domain.Route;
import com.backend.api.domain.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;



@Repository
public interface RouteRepository extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {


  @Query("SELECT x from Route r JOIN r.profiles x WHERE r.id = :routeId")
  List<UserProfile> getProfiles(@Param("routeId") Long id);

  @Query("SELECT p FROM Route r JOIN r.profiles p WHERE r.id = :routeId")
  Page<UserProfile> findProfiles(@Param("routeId") Long id, Pageable pageable);

  @Query("SELECT p FROM UserProfile p WHERE p NOT IN (SELECT distinct p from UserProfile p join p.routes r where r.id = :routeId)")
  Page<UserProfile> findUnlinkedProfiles(@Param("routeId") Long id, Pageable pageable);
}

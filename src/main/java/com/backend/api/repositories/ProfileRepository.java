package com.backend.api.repositories;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository(value = "a")
public interface ProfileRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.routes x WHERE p.id = :profileId")
  Page<Route> getRoutes(@Param("profileId") Long profileId, Pageable pageable);

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.routes x WHERE p.id = :profileId")
  List<Route> getRoutes(@Param("profileId") Long profileId);

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.users x WHERE p.id = :profileId")
  Page<User> getUsers(@Param("profileId") Long id, Pageable pageRequest);

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.users x WHERE p.id = :profileId")
  List<User> getUsers(@Param("profileId") Long id);

}

package com.backend.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

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

@Repository(value = "a")
public interface ProfileRepository extends JpaRepository<UserProfile, Integer>, JpaSpecificationExecutor<UserProfile> {

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.routes x WHERE p.id = :profileId")
  public Page<Route> getRoutes(@Param("profileId") Integer profileId, Pageable pageable);

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.routes x WHERE p.id = :profileId")
  public List<Route> getRoutes(@Param("profileId") Integer profileId);

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.users x WHERE p.id = :profileId")
  public Page<User> getUsers(@Param("profileId") Integer id, Pageable pageRequest);

  @Transactional()
  @Query("SELECT x from UserProfile p JOIN p.users x WHERE p.id = :profileId")
  public List<User> getUsers(@Param("profileId") Integer id);

}

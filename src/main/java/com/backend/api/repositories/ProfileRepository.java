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

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {

  @Transactional()
  @Query("SELECT r from UserProfile p JOIN p.routes r WHERE p.id = :profileId")
  Page<Route> findLinkedRoutes(@Param("profileId") Long profileId, Pageable pageable);

//  @Transactional()
//  @Query("SELECT r from UserProfile p JOIN p.routes r WHERE p.id = :profileId")
//  List<Route> getRoutes(@Param("profileId") Long profileId);

  @Transactional()
  @Query("SELECT u from UserProfile p JOIN p.users u WHERE p.id = :profileId")
  Page<User> getUsers(@Param("profileId") Long id, Pageable pageRequest);

  @Transactional()
  @Query("SELECT u from UserProfile p JOIN p.users u WHERE p.id = :profileId")
  List<User> getUsers(@Param("profileId") Long id);

  @Query("SELECT DISTINCT r from Route r JOIN r.profiles p WHERE p.id <> :profileId")
  Page<Route> findUnlinkedRoutes(@Param("profileId") Long routeId, Pageable pageable);

  @Query("SELECT DISTINCT u from User u JOIN u.userProfiles p WHERE p.id <> :profileId")
  Page<User> findUnlinkedUsers(@Param("profileId") Long id, Pageable pageable);

  @Query("SELECT p from UserProfile p WHERE p.id IN (:ids)")
  List<UserProfile> findByIds(@Param("ids") List<Long> ids);
}
